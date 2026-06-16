package in.neuprakash.SajiloYatra.dto.response;


import lombok.Builder;

@Builder
public record RouteResponseDto(
        Long id,
        String source,
        String destination,
        String distance,
        String estimatedDuration
) {
}
