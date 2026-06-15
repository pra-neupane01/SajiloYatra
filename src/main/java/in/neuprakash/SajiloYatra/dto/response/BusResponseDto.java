package in.neuprakash.SajiloYatra.dto.response;

import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusTypeEnum;
import lombok.Builder;

@Builder
public record BusResponseDto(Long id,
                             String busNumber,
                             int capacity,
                             BusTypeEnum busTypeEnum,
                             BusStatusEnum busStatusEnum) {
}
