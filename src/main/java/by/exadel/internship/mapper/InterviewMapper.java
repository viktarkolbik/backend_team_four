package by.exadel.internship.mapper;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.entity.Interview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewMapper {
    InterviewDTO toInterviewDTO(Interview interview);

    Interview toInterview(InterviewDTO interviewDTO);
}
