package in.neuprakash.SajiloYatra.dto.request;

import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BusRequestDto(
        @NotBlank(message = "Bus Number is required")
        String busNumber,

        @NotNull(message = "Capacity is required")
        @Min(20)
        Integer capacity,

        @NotNull(message = "Bus Type is required")
        BusTypeEnum busTypeEnum,

        @NotNull(message = "Bus Status is required")
        BusStatusEnum busStatusEnum) {
}
