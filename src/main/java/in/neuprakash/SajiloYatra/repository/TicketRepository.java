package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsByBookingId(Long bookingId);

    Optional<Ticket> findByBookingId(Long bookingId);
}