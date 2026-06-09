package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}