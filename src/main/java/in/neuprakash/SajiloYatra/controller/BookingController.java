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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDto addBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.addBooking(bookingRequestDto);
    }

    @GetMapping
    public List<BookingResponseDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public BookingResponseDto updateBooking(@PathVariable Long id,
                                            @RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.updateBooking(id, bookingRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully";
    }

    @GetMapping("/passenger/{passengerId}")
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
