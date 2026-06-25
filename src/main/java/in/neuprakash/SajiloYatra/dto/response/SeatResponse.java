package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.SeatStatus;
import lombok.Builder;

@Builder
public record SeatResponse(
        Long id,
        String seatNumber,
        SeatStatus seatStatus,
        Long tripId,
        Long bookingId) {
}
