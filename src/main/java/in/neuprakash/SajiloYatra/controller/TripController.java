package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.request.TripRequestDto;
import in.neuprakash.SajiloYatra.dto.request.TripSearchRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.TripResponseDto;
import in.neuprakash.SajiloYatra.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public TripResponseDto saveTrip(@Valid @RequestBody TripRequestDto tripRequestDto) {
        return tripService.saveTrip(tripRequestDto);
    }

    @GetMapping
    public List<TripResponseDto> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public TripResponseDto getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/{id}")
    public TripResponseDto updateTrip(@PathVariable Long id,
                                      @RequestBody TripRequestDto tripRequestDto) {
        return tripService.updateTrip(id, tripRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "Trip deleted successfully";
    }

    @GetMapping("/search")
    public APIResponse<PagedResponse<TripResponseDto>> searchTrips(
            @RequestParam(required = false)
            String source,

            @RequestParam(required = false)
            String destination,

            @RequestParam(required = false)
            LocalDate tripDate,

            @ModelAttribute
            PaginationRequest paginationRequest
    ) {
        TripSearchRequest tripSearchRequest = new TripSearchRequest(source, destination, tripDate);

        PagedResponse<TripResponseDto> tripResponseDtoPagedResponse = tripService.searchTrips(tripSearchRequest, paginationRequest);
        return APIResponse.<PagedResponse<TripResponseDto>>builder()
                .data(tripResponseDtoPagedResponse)
                .message("Fetched successfully")
                .status(true)
                .build();
    }
}
