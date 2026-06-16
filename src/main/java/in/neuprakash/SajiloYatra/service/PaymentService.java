package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.PaymentRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PaymentResponseDto;
import in.neuprakash.SajiloYatra.entity.Payment;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.PaymentMapper;
import in.neuprakash.SajiloYatra.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentResponseDto addPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = PaymentMapper.toEntity(paymentRequestDto);
        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.toResponse(savedPayment);
    }

    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(PaymentMapper::toResponse).toList();
    }

    public PaymentResponseDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new BusinessException("Payment not found"));
        return PaymentMapper.toResponse(payment);
    }
}
