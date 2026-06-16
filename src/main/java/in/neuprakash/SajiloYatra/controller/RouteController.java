package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.request.RouteRequestDto;
import in.neuprakash.SajiloYatra.dto.response.RouteResponseDto;
import in.neuprakash.SajiloYatra.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public RouteResponseDto addRoute(@RequestBody RouteRequestDto routeRequestDto) {
        return routeService.addRoute(routeRequestDto);
    }

    @GetMapping
    public List<RouteResponseDto> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public RouteResponseDto getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id);
    }

    @PutMapping("/{id}")
    public RouteResponseDto updateRoute(@PathVariable Long id,
                                        @RequestBody RouteRequestDto routeRequestDto) {
        return routeService.updateRoute(id, routeRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "Route deleted successfully";
    }

}
