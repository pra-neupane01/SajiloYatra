package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TripRepository extends JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip> {


    @Query("""
                SELECT COUNT(t) > 0
                FROM Trip t
                WHERE t.bus.id = :busId
                AND t.departureTime < :arrivalTime
                AND t.arrivalTime > :departureTime
            """)
    boolean existsOverlappingTrip(
            Long busId,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime
    );
}