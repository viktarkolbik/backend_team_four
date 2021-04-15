package by.exadel.internship.service;

import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestInternshipDTO getById(UUID uuid);
    List<GuestInternshipDTO> getAll();

}
