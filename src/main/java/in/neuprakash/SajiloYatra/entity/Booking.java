package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatusEnum bookingStatusEnum;

    @Enumerated(EnumType.STRING)
    private BookingClassEnum bookingClassEnum;

    @ManyToOne
    @JoinColumn(name = "passenger_id", referencedColumnName = "id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @OneToOne()
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

}
