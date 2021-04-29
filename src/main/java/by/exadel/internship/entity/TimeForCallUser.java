package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "time_for_call_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeForCallUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tfcu_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tfcu_start_hour")
    private LocalDateTime startHour;
    @Column(name = "tfcu_end_hour")
    private LocalDateTime endHour;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "tfcu_u_id")
    private User user;

}
