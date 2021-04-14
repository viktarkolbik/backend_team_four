package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "time_for_call")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeForCall {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tfc_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "tfc_start_hour")
    private int startHour;

    @Column(name = "tfc_end_hour")
    private int endHour;

    @Column(name = "fm_id")
    private UUID formId;
}
