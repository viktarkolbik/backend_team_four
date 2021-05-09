package by.exadel.internship.service;

import by.exadel.internship.dto.internship.GuestInternshipDTO;
import by.exadel.internship.dto.internship.BaseInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestInternshipDTO getGuestRepresentationOfInternshipById(UUID id);

    UserInternshipDTO getUserRepresentationOfInternshipById(UUID id);

    List<BaseInternshipDTO> getAll();

    List<UserInternshipDTO> getAllDeleted();

    UserInternshipDTO getDeletedInternshipById(UUID uuid);

    UserInternshipDTO restoreInternshipById(UUID uuid);

    void deleteInternshipById(UUID internshipId);

    UserInternshipDTO saveInternship(UserInternshipDTO internshipDTO);
}
