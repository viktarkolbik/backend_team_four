package by.exadel.internship.mapper;

import by.exadel.internship.dto.interview.InterviewDTO;
import by.exadel.internship.dto.interview.InterviewInfoDTO;
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

    @IterableMapping(qualifiedByName = "interview")
    List<InterviewDTO> mapToInterviewDTO(List<Interview> interviews);

    @Named(value = "interviewInfo")
    InterviewInfoDTO toInterviewInfo(Interview interview);
    Interview fromInterviewInfo(InterviewInfoDTO interviewInfoDTO);

    @IterableMapping(qualifiedByName = "interviewInfo")
    List<InterviewInfoDTO> toInterviewInfoList(List<Interview> interviews);



}
