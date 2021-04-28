package by.exadel.internship.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class FeedbackRequest {
    private UUID userId;
    private String feedback;
}
