package by.exadel.internship.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class FeedbackRequest {
    private UUID userId;
    private String feedback;
}
