package by.exadel.internship.service;

import by.exadel.internship.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAll();
    UserDTO getById(UUID uuid);

}
