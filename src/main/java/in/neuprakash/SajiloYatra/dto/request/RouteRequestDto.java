package in.neuprakash.SajiloYatra.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteRequestDto(
        @NotBlank(message = "Source is required")
        String source,

        @NotBlank(message = "Destination is required")
        String destination,

        @NotNull(message = "Distance is required")
        Integer distance,

        @NotBlank(message = "Estimated Duration is required")
        String estimatedDuration
) {

}
