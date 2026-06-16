package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.PaymentRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PaymentResponseDto;
import in.neuprakash.SajiloYatra.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDto addPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.addPayment(paymentRequestDto);
    }

    @GetMapping
    public List<PaymentResponseDto> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponseDto getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
}
