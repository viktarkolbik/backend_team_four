package by.exadel.internship.mapper;

import by.exadel.internship.dto.time_for_call.UserTimeSlotDTO;
import by.exadel.internship.dto.time_for_call.UserTimeSlotWithUserDTO;
import by.exadel.internship.entity.UserTimeSlot;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTimeSlotMapper {
    @Named(value = "timeForCallUser")
    UserTimeSlot toTimeForCallUserEntity(UserTimeSlotDTO userTimeSlotDTO);
    @Named(value = "timeForCallUserDTO")
    UserTimeSlotDTO toTimeForCallUserDTO(UserTimeSlot userTimeSlot);

    @Named(value = "timeForCallWithUserDTO")
    UserTimeSlot toTimeForCallUserEntity(UserTimeSlotWithUserDTO time);

    @IterableMapping(qualifiedByName = "timeForCallUserDTO")
    List<UserTimeSlotDTO> mapToDTO(List<UserTimeSlot> userTimeSlotList);

    @IterableMapping(qualifiedByName = "timeForCallUser")
    List<UserTimeSlot> map(List<UserTimeSlotDTO> userTimeSlotDTOList);

    @IterableMapping(qualifiedByName = "timeForCallWithUserDTO")
    List<UserTimeSlot> mapToEntity(List<UserTimeSlotWithUserDTO> time);
}
