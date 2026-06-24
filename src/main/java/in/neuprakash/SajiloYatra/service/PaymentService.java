package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.PaymentRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PaymentResponseDto;
import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.Payment;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.PaymentStatus;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.PaymentMapper;
import in.neuprakash.SajiloYatra.repository.BookingRepository;
import in.neuprakash.SajiloYatra.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentResponseDto addPayment(PaymentRequestDto paymentRequestDto) {
        Booking booking = bookingRepository.findById(paymentRequestDto.bookingId()).orElseThrow(() -> new BusinessException("Booking id not found "));
        if (booking.getBookingStatusEnum() != BookingStatusEnum.PENDING) {
            throw new BusinessException("Booking is not pending");
        }
        booking.setBookingStatusEnum(BookingStatusEnum.CONFIRMED);
        bookingRepository.save(booking);

        Payment payment = PaymentMapper.toEntity(paymentRequestDto);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());
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
