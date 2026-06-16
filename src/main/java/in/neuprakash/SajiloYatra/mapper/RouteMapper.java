package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.RouteRequestDto;
import in.neuprakash.SajiloYatra.dto.response.RouteResponseDto;
import in.neuprakash.SajiloYatra.entity.Route;

public class RouteMapper {

    public static Route toEntity(RouteRequestDto routeRequestDto) {
        return Route.builder()
                .source(routeRequestDto.source())
                .destination(routeRequestDto.destination())
                .distance(routeRequestDto.distance())
                .estimatedDuration(routeRequestDto.estimatedDuration())
                .build();

    }

    public static RouteResponseDto toResponse(Route route) {
        return RouteResponseDto.builder()
                .id(route.getId())
                .source(route.getSource())
                .destination(route.getDestination())
                .distance(String.valueOf(route.getDistance()))
                .estimatedDuration(route.getEstimatedDuration())
                .build();

    }
}
