package by.exadel.internship.service.impl;

import by.exadel.internship.dto.InterviewDTO;
import by.exadel.internship.dto.enums.UserRole;
import by.exadel.internship.entity.Interview;
import by.exadel.internship.exception_handing.InappropriateRoleException;
import by.exadel.internship.mapper.InterviewMapper;
import by.exadel.internship.repository.InterviewRepository;
import by.exadel.internship.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewMapper mapper;

    @Override
    public List<InterviewDTO> getAllByUserId(UUID userId, UserRole userRole) {
        List<Interview> interviews;
        switch (userRole){
            case ADMIN:{
                interviews = interviewRepository.findAllByAdmin(userId);
                break;
            }
            case TECH_EXPERT:{
                interviews = interviewRepository.findAllByTechSpecialist(userId);
                break;
            }
            default:{
                throw new InappropriateRoleException("Inappropriate role","user.role.invalid");
            }
        }
        return mapper.mapToInterviewDTO(interviews);
    }
}
