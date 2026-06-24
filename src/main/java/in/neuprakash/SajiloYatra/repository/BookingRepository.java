package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT COALESCE(SUM(b.numberOfSeats), 0) FROM Booking b WHERE b.trip.id = :tripId")
    Integer countBookedSeatsByTripId(Long tripId);

}