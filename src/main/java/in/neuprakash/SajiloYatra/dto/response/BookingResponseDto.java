package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingResponseDto(Long id,
                                 LocalDateTime bookingDate,
                                 BookingStatusEnum bookingStatusEnum,
                                 BookingClassEnum bookingClassEnum,
                                 Long passengerId,
                                 Long tripId,
                                 Long ticketId) {
}
