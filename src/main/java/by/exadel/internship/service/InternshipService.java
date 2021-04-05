package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.exception.NoEntityException;
import by.exadel.internship.mapper.InternshipMapper;
import by.exadel.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper internShipMapper;

    public GuestInternshipDTO getById(UUID uuid) {

        Internship internship = internshipRepository.findById(uuid).orElseThrow(() -> new NoEntityException(uuid));;

        return internShipMapper.toGuestInternshipDTO(internship);

    }

    public List<GuestInternshipDTO> getAll() {

        List<Internship> internships = internshipRepository.findAll();
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);

        return guestInternshipDTOList;

    }
}
