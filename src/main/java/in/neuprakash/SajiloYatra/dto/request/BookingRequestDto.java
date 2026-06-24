package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import jakarta.validation.constraints.NotNull;

public record BookingRequestDto(

        @NotNull(message = "Number of booked seats is required")
        Integer numberOfSeats,

        @NotNull(message = "Booking Class is required")
        BookingClassEnum bookingClassEnum,

        @NotNull(message = "Passenger Id is required")
        Long passengerId,

        @NotNull(message = "Trip Id is required")
        Long tripId
) {
}
