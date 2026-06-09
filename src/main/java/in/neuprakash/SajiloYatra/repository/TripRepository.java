package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}