package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.PaymentMethodEnum;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentResponseDto(Long id,
                                 BigDecimal amount,
                                 LocalDateTime paymentDate,
                                 PaymentMethodEnum paymentMethodEnum,
                                 Long ticketId) {
}
