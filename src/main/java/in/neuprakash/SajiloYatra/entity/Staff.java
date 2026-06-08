package in.neuprakash.SajiloYatra.entity;

import in.neuprakash.SajiloYatra.entity.enums.DesignationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "staffs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private DesignationEnum designation;

    private BigDecimal salary;

    private LocalDate hireDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    @ManyToMany
    @JoinTable(name = "bus_staff",
    joinColumns = @JoinColumn(name = "staff_id"),
    inverseJoinColumns = @JoinColumn(name = "bus_id"))
    private List<Bus> bus;

}
