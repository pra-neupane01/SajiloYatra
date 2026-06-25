package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Seat;
import in.neuprakash.SajiloYatra.entity.Trip;
import in.neuprakash.SajiloYatra.entity.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    boolean existsByTripId(Long tripId);

    boolean existsByBookingId(Long bookingId);


    List<Seat> findByTripIdAndSeatStatus(Long tripId, SeatStatus seatStatus);

    List<Seat> findByTripIdAndSeatNumberIn(Long tripId, List<String> seatNumbers);

    Long trip(Trip trip);
}