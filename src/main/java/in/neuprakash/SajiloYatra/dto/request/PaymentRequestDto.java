package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequestDto(

        @NotNull(message = "Amount is required")
        BigDecimal amount,

        @NotNull(message = "Payment Method is required")
        PaymentMethodEnum paymentMethodEnum,

        @NotNull(message = "Booking id is required")
        Long bookingId
) {
}
