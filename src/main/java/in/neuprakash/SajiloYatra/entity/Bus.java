package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.entity.enums.BusTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private BusTypeEnum busTypeEnum;

    @Enumerated(EnumType.STRING)
    private BusStatusEnum busStatusEnum;
}
