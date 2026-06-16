package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.TripStatusEnum;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TripResponseDto(
        Long id,
        LocalDate tripDate,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        TripStatusEnum tripStatusEnum,
        Long routeId,
        Long busId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
