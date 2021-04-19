package by.exadel.internship.service;

import by.exadel.internship.dto.internshipDTO.GuestInternshipDTO;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestInternshipDTO getById(UUID id);

    List<GuestInternshipDTO> getAll();

    List<GuestInternshipDTO> getAllDeleted();

    GuestInternshipDTO getDeletedInternshipById(UUID uuid);

    GuestInternshipDTO restoreInternshipById(UUID uuid);

    void deleteInternshipById(UUID internshipId);
}
