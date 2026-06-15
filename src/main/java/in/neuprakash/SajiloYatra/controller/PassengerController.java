package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.PassengerRequestDto;
import in.neuprakash.SajiloYatra.dto.response.PassengerResponseDto;
import in.neuprakash.SajiloYatra.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public PassengerResponseDto savePassenger(@Valid @RequestBody PassengerRequestDto passengerRequestDto) {
        return passengerService.savePassenger(passengerRequestDto);
    }

    @GetMapping
    public List<PassengerResponseDto> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public PassengerResponseDto getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PutMapping("/{id}")
    public PassengerResponseDto updatePassenger(@PathVariable Long id,
                                                @RequestBody PassengerRequestDto passengerRequestDto) {
        return passengerService.updatePassenger(id, passengerRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return "Passenger deleted successfully";
    }
}
