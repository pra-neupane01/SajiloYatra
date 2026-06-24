package in.neuprakash.SajiloYatra.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TicketResponseDto(Long id,
                                BigDecimal fare,
                                LocalDateTime issueDate,
                                Long bookingId,
                                Long passengerId,
                                Long tripId) {
}
