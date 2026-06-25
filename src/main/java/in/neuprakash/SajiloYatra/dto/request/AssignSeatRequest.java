package in.neuprakash.SajiloYatra.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record AssignSeatRequest(
        @NotEmpty(message = "Seat numbers are required")
        List<String> seatNumbers
) {
}
