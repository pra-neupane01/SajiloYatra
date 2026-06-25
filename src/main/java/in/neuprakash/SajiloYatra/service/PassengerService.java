package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.PassengerRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.PassengerResponseDto;
import in.neuprakash.SajiloYatra.entity.Passenger;
import in.neuprakash.SajiloYatra.entity.User;
import in.neuprakash.SajiloYatra.entity.enums.RoleEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.PassengerMapper;
import in.neuprakash.SajiloYatra.repository.PassengerRepository;
import in.neuprakash.SajiloYatra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final UserRepository userRepository;

    public PassengerResponseDto savePassenger(PassengerRequestDto passengerRequestDto) {
        if (passengerRepository.existsByCitizenshipNo(
                passengerRequestDto.citizenshipNo())) {
            throw new BusinessException(
                    "Passenger already exists");
        }

        User user = userRepository.findById(passengerRequestDto.userId())
                .orElseThrow(() -> new BusinessException("User not found with the provided id"));

        if (user.getRole() != RoleEnum.PASSENGER) {
            throw new BusinessException("Only users with passenger role can be linked to passenger");
        }

        if (passengerRepository.existsByUserId(user.getId())) {
            throw new BusinessException("Passenger already exists for the provided user");
        }

        Passenger passenger = PassengerMapper.toEntity(passengerRequestDto);
        passenger.setUser(user);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return PassengerMapper.toResponse(savedPassenger);

    }

    public PagedResponse<PassengerResponseDto> getAllPassengers(Pageable pageable) {
        Page<Passenger> passengerPage = passengerRepository.findAll(pageable);
        Page<PassengerResponseDto> passengerResponsePage = passengerPage.map(PassengerMapper::toResponse);
        return PagedResponse.from(passengerResponsePage);
    }

    public PassengerResponseDto getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new BusinessException("Passenger not found with the provided id"));

        return PassengerMapper.toResponse(passenger);
    }

    public PassengerResponseDto updatePassenger(Long id, PassengerRequestDto passengerRequestDto) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new BusinessException("Passenger not found with the provided id"));


        if (passengerRequestDto.citizenshipNo() != null) {
            if (passengerRepository.existsByCitizenshipNoAndIdNot(passengerRequestDto.citizenshipNo(), id)) {
                throw new BusinessException("Passenger already exists");
            }
            passenger.setCitizenshipNo(
                    passengerRequestDto.citizenshipNo());
        }

        if (passengerRequestDto.userId() != null) {
            User user = userRepository.findById(passengerRequestDto.userId())
                    .orElseThrow(() -> new BusinessException("User not found with the provided id"));

            if (user.getRole() != RoleEnum.PASSENGER) {
                throw new BusinessException("Only users with passenger role can be linked to passenger");
            }

            if (!user.getId().equals(passenger.getUser().getId())
                    && passengerRepository.existsByUserId(user.getId())) {
                throw new BusinessException("Passenger already exists for the provided user");
            }

            passenger.setUser(user);
        }

        return PassengerMapper.toResponse(passengerRepository.save(passenger));
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElseThrow(() -> new BusinessException("Passenger not found with the provided id"));
        passengerRepository.delete(passenger);
    }

}

