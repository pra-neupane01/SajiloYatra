package in.neuprakash.SajiloYatra.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "passengers", uniqueConstraints = {@UniqueConstraint(name = "citizenshipConstraint", columnNames = {"citizenshipNo"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger extends BaseEntity {

    @Column(nullable = false)
    private String citizenshipNo;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "passenger",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings;


}
