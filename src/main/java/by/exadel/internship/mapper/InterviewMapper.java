package by.exadel.internship.mapper;

import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.interview.InterviewWithUserNameDTO;
import by.exadel.internship.entity.Interview;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    @Named(value = "interview")
    InterviewDTO toInterviewDTO(Interview interview);

    Interview toInterview(InterviewDTO interviewDTO);

    @Named(value = "interviewWithUser")
    InterviewWithUserNameDTO toInterviewWithUserDTO(Interview interview);

    Interview toInterviewFromInterviewWithUserDTO(InterviewWithUserNameDTO interview);

    @IterableMapping(qualifiedByName = "interview")
    List<InterviewDTO> mapToInterviewDTO(List<Interview> interviews);

    @IterableMapping(qualifiedByName = "interviewWithUser")
    List<InterviewWithUserNameDTO> mapToInterviewWithUserDTO(List<Interview> interviews);
}
