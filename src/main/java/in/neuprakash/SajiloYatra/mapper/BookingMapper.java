package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.BookingRequestDto;
import in.neuprakash.SajiloYatra.dto.response.BookingResponseDto;
import in.neuprakash.SajiloYatra.entity.Booking;

public class BookingMapper {
    public static Booking toEntity(BookingRequestDto bookingRequestDto) {
        return Booking.builder()
                .bookingDate(bookingRequestDto.bookingDate())
                .bookingStatusEnum(bookingRequestDto.bookingStatusEnum())
                .bookingClassEnum(bookingRequestDto.bookingClassEnum())
                .passenger(null)
                .trip(null)
                .ticket(null)
                .build();
    }

    public static BookingResponseDto toResponse(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .bookingStatusEnum(booking.getBookingStatusEnum())
                .bookingClassEnum(booking.getBookingClassEnum())
                .passengerId(booking.getPassenger() != null ? booking.getPassenger().getId() : null)
                .tripId(booking.getTrip() != null ? booking.getTrip().getId() : null)
                .ticketId(booking.getTicket() != null ? booking.getTicket().getId() : null)
                .build();
    }
}
