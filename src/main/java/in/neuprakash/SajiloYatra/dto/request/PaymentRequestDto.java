package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentRequestDto(
        @NotNull(message = "Amount is required")
        BigDecimal amount,

        @NotNull(message = "Payment date is required")
        LocalDateTime paymentDate,

        @NotNull(message = "Payment Method is required")
        PaymentMethodEnum paymentMethodEnum,

        @NotNull(message = "Ticket id is required")
        Long ticketId,

        @NotNull(message = "Booking id is required")
        Long bookingId
) {

}
