package in.neuprakash.SajiloYatra.repository;

import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COALESCE(SUM(b.numberOfSeats), 0) FROM Booking b WHERE b.trip.id = :tripId")
    Integer countBookedSeatsByTripId(Long tripId);

    Page<Booking> findByPassengerId(Long passengerId, Pageable pageable);

    Page<Booking> findByTripId(Long tripId, Pageable pageable);

    Long countByBookingStatusEnum(BookingStatusEnum bookingStatusEnum);
}