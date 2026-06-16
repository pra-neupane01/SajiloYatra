package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.BookingRequestDto;
import in.neuprakash.SajiloYatra.dto.response.BookingResponseDto;
import in.neuprakash.SajiloYatra.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
