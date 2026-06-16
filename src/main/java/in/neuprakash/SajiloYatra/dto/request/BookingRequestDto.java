package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookingRequestDto(
        @NotNull(message = "Booking Date is required")
        LocalDateTime bookingDate,

        @NotNull(message = "Booking Status is required")
        BookingStatusEnum bookingStatusEnum,

        @NotNull(message = "Booking Class is required")
        BookingClassEnum bookingClassEnum,

        @NotNull(message = "Passenger Id is required")
        Long passengerId,

        @NotNull(message = "Trip Id is required")
        Long tripId,

        @NotNull(message = "Ticket Id is required")
        Long ticketId
) {
}
