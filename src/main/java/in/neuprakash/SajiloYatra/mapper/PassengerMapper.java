package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.PassengerRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PassengerResponseDto;
import in.neuprakash.SajiloYatra.entity.Passenger;

public class PassengerMapper {
    public static Passenger toEntity(PassengerRequestDto passengerRequestDto) {
        return Passenger.builder()
                .citizenshipNo(passengerRequestDto.citizenshipNo())
                .preferences(passengerRequestDto.preferences())
                .build();


    }

    public static PassengerResponseDto toResponse(Passenger passenger) {
        return PassengerResponseDto.builder()
                .id(passenger.getId())
                .userId(passenger.getUser().getId())
                .citizenshipNo(passenger.getCitizenshipNo())
                .preferences(passenger.getPreferences())
                .createdAt(passenger.getCreatedAt())
                .updatedAt(passenger.getUpdatedAt())
                .build();

    }
}
