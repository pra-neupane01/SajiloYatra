package in.neuprakash.SajiloYatra.service;


import in.neuprakash.SajiloYatra.dto.request.RouteRequestDto;
import in.neuprakash.SajiloYatra.dto.response.RouteResponseDto;
import in.neuprakash.SajiloYatra.entity.Route;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.RouteMapper;
import in.neuprakash.SajiloYatra.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteResponseDto addRoute(RouteRequestDto routeRequestDto) {
        Route route = RouteMapper.toEntity(routeRequestDto);
        Route savedRoute = routeRepository.save(route);
        return RouteMapper.toResponse(savedRoute);
    }

    public List<RouteResponseDto> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes.stream().map(RouteMapper::toResponse).toList();
    }

    public RouteResponseDto getRouteById(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new BusinessException("Route does not exist with the provided id"));
        return RouteMapper.toResponse(route);
    }

    public RouteResponseDto updateRoute(Long id, RouteRequestDto routeRequestDto) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new BusinessException("Route does not exist with the provided id"));
        if (routeRequestDto.source() != null) {
            route.setSource(routeRequestDto.source());
        }
        if (routeRequestDto.destination() != null) {
            route.setDestination(routeRequestDto.destination());
        }
        if (routeRequestDto.estimatedDuration() != null) {
            route.setEstimatedDuration(routeRequestDto.estimatedDuration());
        }
        if (routeRequestDto.distance() != null) {
            route.setDistance(routeRequestDto.distance());
        }
        return RouteMapper.toResponse(routeRepository.save(route));


    }

    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new BusinessException("Route does not exist with the provided id"));


        routeRepository.deleteById(id);
    }
}
