package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.BookingRequestDto;
import in.neuprakash.SajiloYatra.dto.response.BookingResponseDto;
import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.Passenger;
import in.neuprakash.SajiloYatra.entity.Ticket;
import in.neuprakash.SajiloYatra.entity.Trip;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.BookingMapper;
import in.neuprakash.SajiloYatra.repository.BookingRepository;
import in.neuprakash.SajiloYatra.repository.PassengerRepository;
import in.neuprakash.SajiloYatra.repository.TicketRepository;
import in.neuprakash.SajiloYatra.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final TripRepository tripRepository;
    private final TicketRepository ticketRepository;

    public BookingResponseDto addBooking(BookingRequestDto bookingRequestDto) {
        Passenger passenger = passengerRepository.findById(bookingRequestDto.passengerId())
                .orElseThrow(() -> new BusinessException("Passenger not found"));

        Trip trip = tripRepository.findById(bookingRequestDto.tripId())
                .orElseThrow(() -> new BusinessException("Trip not found"));

        Integer bookedSeatsNumber = bookingRepository.countBookedSeatsByTripId(trip.getId());
        int totalSeats = trip.getBus().getCapacity();

        if (bookedSeatsNumber + bookingRequestDto.numberOfSeats() > totalSeats) {
            throw new BusinessException("Seat not available");
        }
        Booking booking = BookingMapper.toEntity(bookingRequestDto);
        booking.setPassenger(passenger);
        booking.setTrip(trip);
        booking.setNumberOfSeats(bookingRequestDto.numberOfSeats());
        booking.setBookingStatusEnum(BookingStatusEnum.PENDING);
        booking.setBookingDate(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);

        return BookingMapper.toResponse(savedBooking);
    }

    public List<BookingResponseDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingMapper::toResponse).toList();
    }

    public BookingResponseDto getBookingById(Long id) {
        Booking booking = findBookingById(id);
        return BookingMapper.toResponse(booking);
    }

    public BookingResponseDto updateBooking(Long id,
                                            BookingRequestDto bookingRequestDto) {
        Booking booking = findBookingById(id);

        if (bookingRequestDto.bookingClassEnum() != null) {
            booking.setBookingClassEnum(bookingRequestDto.bookingClassEnum());
        }
        if (bookingRequestDto.passengerId() != null) {
            booking.setPassenger(getPassengerById(bookingRequestDto.passengerId()));
        }
        if (bookingRequestDto.tripId() != null) {
            booking.setTrip(getTripById(bookingRequestDto.tripId()));
        }
        return BookingMapper.toResponse(bookingRepository.save(booking));
    }

    public void deleteBooking(Long id) {
        Booking booking = findBookingById(id);
        bookingRepository.delete(booking);
    }

    private Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Booking not found with the provided id"));
    }

    private Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new BusinessException("Passenger not found with the provided id"));
    }

    private Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new BusinessException("Trip not found with the provided id"));
    }

    private Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new BusinessException("Ticket not found with the provided id"));
    }

//    private void validateTicketAssignment(Long ticketId, Long bookingId) {
//        boolean ticketAlreadyAssigned = bookingRepository.findAll().stream()
//                .anyMatch(existingBooking ->
//                        existingBooking.getTicket() != null
//                                && existingBooking.getTicket().getId().equals(ticketId)
//                                && (!existingBooking.getId().equals(bookingId)));
//
//        if (ticketAlreadyAssigned) {
//            throw new BusinessException("Ticket already assigned to another booking");
//        }
//    }
}
