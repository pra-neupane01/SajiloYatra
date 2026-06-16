package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.TripStatusEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TripRequestDto(
        @NotNull(message = "Trip Date is required")
        LocalDate tripDate,

        @NotNull(message = "Departure Time is required")
        LocalDateTime departureTime,

        @NotNull(message = "Arrival Time is required")
        LocalDateTime arrivalTime,

        @NotNull(message = "Trip Status is required")
        TripStatusEnum tripStatusEnum,

        @NotNull(message = "Route Id is required")
        Long routeId,

        @NotNull(message = "Bus Id is required")
        Long busId
) {
}
