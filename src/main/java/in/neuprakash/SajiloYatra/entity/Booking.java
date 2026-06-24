package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    @Column()
    private LocalDateTime bookingDate;

    @Column(nullable = false)
    private Integer numberOfSeats;

    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatusEnum;

    @Enumerated(EnumType.STRING)
    private BookingClassEnum bookingClassEnum;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

}
