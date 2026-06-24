package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.PaymentRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PaymentResponseDto;
import in.neuprakash.SajiloYatra.entity.Payment;

public class PaymentMapper {
    public static Payment toEntity(PaymentRequestDto paymentRequestDto) {
        return Payment.builder().amount(paymentRequestDto.amount())
                .paymentMethodEnum(paymentRequestDto.paymentMethodEnum())
                .build();

    }

    public static PaymentResponseDto toResponse(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .paymentMethodEnum(payment.getPaymentMethodEnum())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
