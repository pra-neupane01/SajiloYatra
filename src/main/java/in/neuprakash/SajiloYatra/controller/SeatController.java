package in.neuprakash.SajiloYatra.controller;


import in.neuprakash.SajiloYatra.dto.request.AssignSeatRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.SeatResponse;
import in.neuprakash.SajiloYatra.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/generate/{tripId}")
    public APIResponse<List<SeatResponse>> generateSeatsForTrip(@PathVariable Long tripId) {
        return APIResponse.<List<SeatResponse>>builder()
                .status(true)
                .message("Seats generated successfully")
                .data(seatService.generateSeatsForTrip(tripId))
                .build();
    }

    @GetMapping("/trip/{tripId}/available")
    public APIResponse<List<SeatResponse>> getAvailableSeats(@PathVariable Long tripId) {
        return APIResponse.<List<SeatResponse>>builder()
                .status(true)
                .message("Available seats fetched successfully")
                .data(seatService.getAvailableSeats(tripId))
                .build();
    }

    @PostMapping("/assign/{bookingId}")
    public APIResponse<List<SeatResponse>> assignSeats(
            @PathVariable Long bookingId,
            @Valid @RequestBody AssignSeatRequest requestDto
    ) {
        return APIResponse.<List<SeatResponse>>builder()
                .status(true)
                .message("Seats assigned successfully")
                .data(seatService.assignSeats(bookingId, requestDto))
                .build();
    }

    @GetMapping("/booking/{bookingId}")
    public APIResponse<List<SeatResponse>> getSeatsByBooking(@PathVariable Long bookingId) {
        return APIResponse.<List<SeatResponse>>builder()
                .status(true)
                .message("Booking seats fetched successfully")
                .data(seatService.getSeatsByBooking(bookingId))
                .build();
    }
}