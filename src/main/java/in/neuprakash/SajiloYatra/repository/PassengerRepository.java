package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}