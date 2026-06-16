package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.request.TripRequestDto;
import in.neuprakash.SajiloYatra.dto.response.TripResponseDto;
import in.neuprakash.SajiloYatra.entity.Trip;

public class TripMapper {
    public static Trip toEntity(TripRequestDto tripRequestDto) {
        Trip trip = new Trip();
        trip.setTripDate(tripRequestDto.tripDate());
        trip.setDepartureTime(tripRequestDto.departureTime());
        trip.setArrivalTime(tripRequestDto.arrivalTime());
        trip.setTripStatusEnum(tripRequestDto.tripStatusEnum());
        return trip;
    }

    public static TripResponseDto toResponse(Trip trip) {
        return TripResponseDto.builder()
                .id(trip.getId())
                .tripDate(trip.getTripDate())
                .departureTime(trip.getDepartureTime())
                .arrivalTime(trip.getArrivalTime())
                .tripStatusEnum(trip.getTripStatusEnum())
                .routeId(trip.getRoute() != null ? trip.getRoute().getId() : null)
                .busId(trip.getBus() != null ? trip.getBus().getId() : null)
                .createdAt(trip.getCreatedAt())
                .updatedAt(trip.getUpdatedAt())
                .build();
    }
}
