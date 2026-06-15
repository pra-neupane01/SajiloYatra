package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.BusRequestDto;
import in.neuprakash.SajiloYatra.dto.response.BusResponseDto;
import in.neuprakash.SajiloYatra.entity.Bus;

public class BusMapper {
    public static Bus toEntity(BusRequestDto busRequestDto) {
        return Bus.builder()
                .busNumber(busRequestDto.busNumber())
                .capacity(busRequestDto.capacity())
                .busStatusEnum(busRequestDto.busStatusEnum())
                .busTypeEnum(busRequestDto.busTypeEnum())
                .build();
    }

    public static BusResponseDto toResponse(Bus bus) {
        return BusResponseDto.builder()
                .id(bus.getId())
                .busNumber(bus.getBusNumber())
                .capacity(bus.getCapacity())
                .busStatusEnum(bus.getBusStatusEnum())
                .busTypeEnum(bus.getBusTypeEnum())
                .build();

    }
}
