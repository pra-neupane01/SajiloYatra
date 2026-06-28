package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByBookingId(Long bookingId);

    Optional<Payment> findByBookingId(Long bookingId);

    @Query("""
                     SELECT COALESCE(SUM(p.amount),0) \s
                     FROM Payment p
                     WHERE p.paymentStatus = 'SUCCESS'
            \s""")
    BigDecimal getTotalRevenue();
}