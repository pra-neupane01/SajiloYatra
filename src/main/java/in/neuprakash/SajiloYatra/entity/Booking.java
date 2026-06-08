package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.BookingClassEnum;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
