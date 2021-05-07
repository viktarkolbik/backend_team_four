package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_time_slot")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTimeSlot {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ust_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "ust_start_date")
    private LocalDateTime startDate;
    @Column(name = "ust_end_date")
    private LocalDateTime endDate;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "ust_u_id")
    private User user;

}
