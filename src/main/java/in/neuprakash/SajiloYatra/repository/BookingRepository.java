package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}