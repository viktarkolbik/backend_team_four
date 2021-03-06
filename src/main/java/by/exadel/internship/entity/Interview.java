package by.exadel.internship.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interview")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Interview {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "i_id", updatable = false, nullable = false)
    private UUID id;

    @JoinColumn(name = "i_admin_id")
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User admin;

    @Column(name = "i_admin_interview_date")
    private LocalDateTime adminInterviewDate;

    @Column(name = "i_admin_feedback")
    private String adminFeedback;

    @JoinColumn(name = "i_tech_id")
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User techSpecialist;
    @Column(name = "i_tech_interview_date")
    private LocalDateTime techInterviewDate;

    @Column(name = "i_tech_feedback")
    private String techFeedback;

}
