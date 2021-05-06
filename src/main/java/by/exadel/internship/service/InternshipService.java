package by.exadel.internship.service;

import by.exadel.internship.dto.internship.GuestFullInternshipDTO;
import by.exadel.internship.dto.internship.GuestShortInternshipDTO;
import by.exadel.internship.dto.internship.UserInternshipDTO;

import java.util.List;
import java.util.UUID;

public interface InternshipService {

    GuestFullInternshipDTO getById(UUID id);

    List<GuestShortInternshipDTO> getAll();

    List<UserInternshipDTO> getAllDeleted();

    UserInternshipDTO getDeletedInternshipById(UUID uuid);

    UserInternshipDTO restoreInternshipById(UUID uuid);

    void deleteInternshipById(UUID internshipId);

    UserInternshipDTO saveInternship(UserInternshipDTO internshipDTO);

    boolean addUser(UUID userId, UUID internshipId);
}
