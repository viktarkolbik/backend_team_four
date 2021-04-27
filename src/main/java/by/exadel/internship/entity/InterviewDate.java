package by.exadel.internship.entity;

import liquibase.pro.packaged.C;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interview_dates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "i_dates_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "i_dates_date_time")
    private LocalDateTime dateTime;
    @Column(name = "i_dates_user_id")
    private UUID userId;
    @Column(name = "i_dates_interview_id")
    private UUID interviewId;
}
