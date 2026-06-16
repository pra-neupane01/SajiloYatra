package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.TripRequestDto;
import in.neuprakash.SajiloYatra.dto.response.TripResponseDto;
import in.neuprakash.SajiloYatra.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
