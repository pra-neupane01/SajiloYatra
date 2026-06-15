package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.BusRequestDto;
import in.neuprakash.SajiloYatra.dto.response.BusResponseDto;
import in.neuprakash.SajiloYatra.entity.Bus;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.BusMapper;
import in.neuprakash.SajiloYatra.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;

    public BusResponseDto saveBus(BusRequestDto busRequestDto) {
        Bus bus = BusMapper.toEntity(busRequestDto);
        Bus savedBus = busRepository.save(bus);
        return BusMapper.toResponse(savedBus);
    }

    public List<BusResponseDto> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        return buses.stream().map(BusMapper::toResponse).toList();
    }

    public BusResponseDto getBusById(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusinessException("Bus not found with the provided id"));
        return BusMapper.toResponse(bus);
    }

    public BusResponseDto updateBus(Long id,
                                    BusRequestDto busRequestDto) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusinessException("Bus not found with the provided id"));
        validateBusUpdate(bus, busRequestDto);

        if (busRequestDto.capacity() != null) {
            bus.setCapacity(busRequestDto.capacity());
        }
        if (busRequestDto.busStatusEnum() != null) {
            bus.setBusStatusEnum(busRequestDto.busStatusEnum());
        }
        return BusMapper.toResponse(busRepository.save(bus));
    }

    private void validateBusUpdate(Bus bus, BusRequestDto busRequestDto) {
        if (busRequestDto.busNumber() != null) {
            if (busRequestDto.busNumber().isBlank()) {
                throw new BusinessException("Bus number cannot be blank");
            }
            if (!busRequestDto.busNumber().equals(bus.getBusNumber())) {
                throw new BusinessException("Bus number cannot be changed once issued");
            }
        }

        if (busRequestDto.capacity() != null && busRequestDto.capacity() < 20) {
            throw new BusinessException("Capacity must be at least 20");
        }

        if (busRequestDto.busTypeEnum() != null && busRequestDto.busTypeEnum() != bus.getBusTypeEnum()) {
            throw new BusinessException("Bus type cannot be changed once issued");
        }
    }

    public void deleteBus(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new BusinessException("Bus not found with the provided id"));
        busRepository.deleteById(id);
    }
}
