package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception_handing.NotFoundException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipService {

    @PersistenceContext
    private EntityManager entityManager;

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internShipMapper;

    public GuestInternshipDTO getById(UUID uuid) {
        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get Internships with uuid=" + uuid);
        Internship internship = internshipRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("No such Internship with uuid = "+ uuid + " in DB", "uuid.invalid"));
        log.info("Successfully got Internships with uuid=" + uuid);
        return internShipMapper.toGuestInternshipDTO(internship);
    }

    public List<GuestInternshipDTO> getAll(Boolean isDeleted) {
        MDC.put("className", InternshipService.class.getSimpleName());
        log.info("Try to get all Internships");
        List<Internship> internships = findAllFilter(isDeleted);
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);
        log.info("Successfully list of Internships");
        return guestInternshipDTOList;

    }

    public List<Internship> findAllFilter(Boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedInternshipFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Internship> internships = internshipRepository.findAll();
        session.disableFilter("deletedInternshipFilter");
        return internships;
    }

    public Boolean deleteInternship(UUID internshipId){
        internshipRepository.deleteById(internshipId);
        return true;
    }
}
