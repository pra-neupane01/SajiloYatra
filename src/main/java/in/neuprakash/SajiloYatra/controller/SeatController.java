package in.neuprakash.SajiloYatra.controller;


import in.neuprakash.SajiloYatra.dto.request.AssignSeatRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.SeatResponse;
import in.neuprakash.SajiloYatra.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/generate/{tripId}")
    @PreAuthorize("hasAuthority('SEAT_GENERATE')")
    public APIResponse<List<SeatResponse>> generateSeatsForTrip(@PathVariable Long tripId) {
        return APIResponse.<List<SeatResponse>>builder()
                .success(true)
                .message("Seats generated successfully")
                .data(seatService.generateSeatsForTrip(tripId))
                .build();
    }

    @GetMapping("/trip/{tripId}/available")
    @PreAuthorize("hasAuthority('SEAT_VIEW')")
    public APIResponse<List<SeatResponse>> getAvailableSeats(@PathVariable Long tripId) {
        return APIResponse.<List<SeatResponse>>builder()
                .success(true)
                .message("Available seats fetched successfully")
                .data(seatService.getAvailableSeats(tripId))
                .build();
    }

    @PostMapping("/assign/{bookingId}")
    @PreAuthorize("hasAuthority('SEAT_ASSIGN')")
    public APIResponse<List<SeatResponse>> assignSeats(
            @PathVariable Long bookingId,
            @Valid @RequestBody AssignSeatRequest requestDto
    ) {
        return APIResponse.<List<SeatResponse>>builder()
                .success(true)
                .message("Seats assigned successfully")
                .data(seatService.assignSeats(bookingId, requestDto))
                .build();
    }

    @GetMapping("/booking/{bookingId}")
    @PreAuthorize("hasAuthority('SEAT_VIEW')")
    public APIResponse<List<SeatResponse>> getSeatsByBooking(@PathVariable Long bookingId) {
        return APIResponse.<List<SeatResponse>>builder()
                .success(true)
                .message("Booking seats fetched successfully")
                .data(seatService.getSeatsByBooking(bookingId))
                .build();
    }
}