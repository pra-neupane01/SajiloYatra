package in.neuprakash.SajiloYatra.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TicketResponseDto(Long id,
                                String ticketNumber,
                                BigDecimal fare,
                                LocalDateTime issueDate) {
}
