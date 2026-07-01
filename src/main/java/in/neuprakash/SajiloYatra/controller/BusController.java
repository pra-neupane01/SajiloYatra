package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.BusRequestDto;
import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.BusResponseDto;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.service.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buses")
public class BusController {
    private final BusService busService;

    @PostMapping
    @PreAuthorize("hasAuthority('BUS_CREATE')")
    public ResponseEntity<APIResponse<BusResponseDto>> createBus(@RequestBody @Valid BusRequestDto busRequestDto) {
        BusResponseDto busResponseDto = busService.saveBus(busRequestDto);
        return ResponseEntity.ok().body(APIResponse.<BusResponseDto>builder().success(true).message("Bus Added successfully.").data(busResponseDto).build());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('BUS_VIEW')")
    public ResponseEntity<APIResponse<PagedResponse<BusResponseDto>>> getAllBuses(@ModelAttribute PaginationRequest paginationRequest) {
        PagedResponse<BusResponseDto> allBuses = busService.getAllBuses(paginationRequest.toPageable());
        APIResponse<PagedResponse<BusResponseDto>> apiResponse = APIResponse.<PagedResponse<BusResponseDto>>builder()
                .success(true)
                .message("Bus fetched successfully")
                .data(allBuses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('BUS_VIEW')")
    public BusResponseDto getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('BUS_UPDATE')")
    public BusResponseDto updateBus(@PathVariable Long id,
                                    @RequestBody BusRequestDto busRequestDto) {
        return busService.updateBus(id, busRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('BUS_DELETE')")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return "Bus deleted successfully";
    }
}
