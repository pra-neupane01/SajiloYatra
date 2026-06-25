package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.AssignSeatRequest;
import in.neuprakash.SajiloYatra.dto.response.SeatResponse;
import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.Seat;
import in.neuprakash.SajiloYatra.entity.Trip;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.SeatStatus;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.SeatMapper;
import in.neuprakash.SajiloYatra.repository.BookingRepository;
import in.neuprakash.SajiloYatra.repository.SeatRepository;
import in.neuprakash.SajiloYatra.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public List<SeatResponse> generateSeatsForTrip(Long tripId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new BusinessException("Trip not found"));

        if (seatRepository.existsByTripId(tripId)) {
            throw new BusinessException("Seats already generated for this trip");
        }

        int capacity = trip.getBus().getCapacity();

        if (capacity <= 0) {
            throw new BusinessException("Bus capacity must be greater than zero");
        }

        List<Seat> seats = new java.util.ArrayList<>();

        for (int i = 1; i <= capacity; i++) {
            Seat seat = Seat.builder()
                    .seatNumber("A" + i)
                    .seatStatus(SeatStatus.AVAILABLE)
                    .trip(trip)
                    .booking(null)
                    .build();

            seats.add(seat);
        }

        return seatRepository.saveAll(seats)
                .stream()
                .map(SeatMapper::toResponse)
                .toList();
    }

    public List<SeatResponse> getAvailableSeats(Long tripId) {

        tripRepository.findById(tripId)
                .orElseThrow(() -> new BusinessException("Trip not found"));

        return seatRepository.findByTripIdAndSeatStatus(tripId, SeatStatus.AVAILABLE)
                .stream()
                .map(SeatMapper::toResponse)
                .toList();
    }

    @Transactional
    public List<SeatResponse> assignSeats(Long bookingId, AssignSeatRequest requestDto) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException("Booking not found"));

        if (booking.getBookingStatusEnum() != BookingStatusEnum.CONFIRMED) {
            throw new BusinessException("Seats can be assigned only for confirmed booking");
        }

        if (seatRepository.existsByBookingId(bookingId)) {
            throw new BusinessException("Seats already assigned for this booking");
        }

        List<String> seatNumbers = requestDto.seatNumbers();

        Set<String> uniqueSeatNumbers = new HashSet<>(seatNumbers);

        if (uniqueSeatNumbers.size() != seatNumbers.size()) {
            throw new BusinessException("Duplicate seat numbers are not allowed");
        }

        if (seatNumbers.size() != booking.getNumberOfSeats()) {
            throw new BusinessException("Selected seat count must match booked seat count");
        }

        Long tripId = booking.getTrip().getId();

        List<Seat> seats = seatRepository.findByTripIdAndSeatNumberIn(tripId, seatNumbers);

        if (seats.size() != seatNumbers.size()) {
            throw new BusinessException("One or more selected seats are invalid");
        }

        for (Seat seat : seats) {
            if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                throw new BusinessException("Seat " + seat.getSeatNumber() + " is not available");
            }

            seat.setSeatStatus(SeatStatus.BOOKED);
            seat.setBooking(booking);
        }

        return seatRepository.saveAll(seats)
                .stream()
                .map(SeatMapper::toResponse)
                .toList();
    }

    public List<SeatResponse> getSeatsByBooking(Long bookingId) {

        bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException("Booking not found"));

        return seatRepository.findAll()
                .stream()
                .filter(seat -> seat.getBooking() != null
                        && seat.getBooking().getId().equals(bookingId))
                .map(SeatMapper::toResponse)
                .toList();
    }
}