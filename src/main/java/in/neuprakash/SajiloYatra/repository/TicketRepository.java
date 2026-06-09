package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}