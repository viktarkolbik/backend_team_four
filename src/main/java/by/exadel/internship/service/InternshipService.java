package by.exadel.internship.service;

import by.exadel.internship.dto.internship.GuestFullInternshipDTO;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;
import by.exadel.internship.entity.Internship;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestFullInternshipDTO getGuestFullInternshipDTO (UUID id);

    UserInternshipDTO getUserInternshipDTO (UUID id);

    List<GuestShortInternshipDTO> getAll();

    List<UserInternshipDTO> getAllDeleted();

    UserInternshipDTO getDeletedInternshipById(UUID uuid);

    UserInternshipDTO restoreInternshipById(UUID uuid);

    void deleteInternshipById(UUID internshipId);

    UserInternshipDTO saveInternship(UserInternshipDTO internshipDTO);
}
