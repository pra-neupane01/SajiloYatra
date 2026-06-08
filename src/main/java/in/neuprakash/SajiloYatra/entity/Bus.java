package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private BusTypeEnum busTypeEnum;

    @Enumerated(EnumType.STRING)
    private BusStatusEnum busStatusEnum;

    @ManyToMany(mappedBy = "bus")
    private List<Staff> staff;


}
