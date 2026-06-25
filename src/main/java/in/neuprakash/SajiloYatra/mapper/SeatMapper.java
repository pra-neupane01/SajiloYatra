package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.response.SeatResponse;
import in.neuprakash.SajiloYatra.entity.Seat;

public class SeatMapper {
    public static SeatResponse toResponse(Seat seat) {
        return SeatResponse.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .seatStatus(seat.getSeatStatus())
                .bookingId(seat.getBooking() != null ? seat.getBooking().getId() : null)
                .tripId(seat.getTrip() != null ? seat.getTrip().getId() : null)
                .build();
    }

//    public static Seat toEntity(SeatResponse seatResponse) {
//        return Seat.builder()
//                .seatNumber(seatResponse.seatNumber())
//                .build();
//    }
}
