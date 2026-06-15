package in.neuprakash.SajiloYatra.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerRequestDto(
        @NotNull(message = "User id is required.")
        Long userId,

        @NotBlank(message = "CitizenshipNo is Required field.")
        String citizenshipNo,

        String preferences) {

}
