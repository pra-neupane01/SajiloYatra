package in.neuprakash.SajiloYatra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @Column(nullable = false)
    private BigDecimal fare;

    @Column(nullable = false)
    private LocalDateTime issueDate;


}
