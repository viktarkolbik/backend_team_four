package by.exadel.internship.service;


import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;
import by.exadel.internship.entity.Internship;
import by.exadel.internship.mapper.InternShipMapper;
import by.exadel.internship.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternShipMapper internShipMapper;

    @Autowired
    public InternshipService(InternshipRepository internshipRepository, InternShipMapper internShipMapper) {
        this.internshipRepository = internshipRepository;
        this.internShipMapper = internShipMapper;
    }


    public GuestInternshipDTO getById(UUID uuid) {

        Internship internship = internshipRepository.getOne(uuid);
        GuestInternshipDTO guestInternshipDTO = internShipMapper.toGuestInternshipDTO(internship);

        return guestInternshipDTO;
    }

    public List<GuestInternshipDTO> getAll(){

        List<Internship> internships = internshipRepository.findAll();
        List<GuestInternshipDTO> guestInternshipDTOList = internShipMapper.map(internships);

        return guestInternshipDTOList;
    }


}
