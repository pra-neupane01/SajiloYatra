package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.BookingRequestDto;
import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.BookingResponseDto;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasAuthority('BOOKING_CREATE')")
    public BookingResponseDto addBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.addBooking(bookingRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('BOOKING_VIEW')")
    public List<BookingResponseDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('BOOKING_VIEW')")
    public BookingResponseDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('BOOKING_UPDATE')")
    public BookingResponseDto updateBooking(@PathVariable Long id,
                                            @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.updateBooking(id, bookingRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('BOOKING_DELETE')")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully";
    }

    @GetMapping("/passenger/{passengerId}")
    @PreAuthorize("hasAuthority('BOOKING_VIEW')")
    public APIResponse<PagedResponse<BookingResponseDto>> getBookingsByPassenger(@PathVariable Long passengerId,
                                                                                 @ModelAttribute PaginationRequest paginationRequest, Pageable pageable) {

        PagedResponse<BookingResponseDto> bookingPage = bookingService.getBookingByPassenger(passengerId, paginationRequest.toPageable());
        return APIResponse.<PagedResponse<BookingResponseDto>>builder()
                .success(true)
                .message("Passenger bookings fetched successfully")
                .data(bookingPage)
                .build();
    }

    @GetMapping("/trips/{tripId}")
    @PreAuthorize("hasAuthority('BOOKING_VIEW')")
    public APIResponse<PagedResponse<BookingResponseDto>> getBookingsByTrip(@PathVariable Long tripId,
                                                                            @ModelAttribute PaginationRequest paginationRequest) {
        PagedResponse<BookingResponseDto> bookingPage = bookingService.getBookingByTrip(tripId, paginationRequest.toPageable());
        return APIResponse.<PagedResponse<BookingResponseDto>>builder()
                .success(true)
                .message("Bookings for trips fetched successfully")
                .data(bookingPage)
                .build();

    }
}
