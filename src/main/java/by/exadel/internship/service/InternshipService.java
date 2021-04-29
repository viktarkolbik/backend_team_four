package by.exadel.internship.service;

import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.ShortInternshipDTO;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestInternshipDTO getById(UUID id);

    List<ShortInternshipDTO> getAll();

    List<GuestInternshipDTO> getAllDeleted();

    GuestInternshipDTO getDeletedInternshipById(UUID uuid);

    GuestInternshipDTO restoreInternshipById(UUID uuid);

    void deleteInternshipById(UUID internshipId);

    GuestInternshipDTO saveInternship(GuestInternshipDTO internshipDTO);
}
