package by.exadel.internship.service;


import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserIdDTO;

import java.util.List;
import java.util.UUID;

public interface UserTimeSlotService {
    void saveUserTime(List<UserTimeSlotDTO> userTimeSlotDTOList, UUID userId);
    void deletedById(UUID timeId);
    void restoreUserTime(UserTimeSlotWithUserIdDTO time);
}
