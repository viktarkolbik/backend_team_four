package by.exadel.internship.mapper;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.dto.TimeForCallWithUserDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.entity.TimeForCallUser;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeForCallUserMapper {
    @Named(value = "timeForCallUser")
    TimeForCallUser toTimeForCallUserEntity(TimeForCallUserDTO timeForCallUserDTO);
    @Named(value = "timeForCallUserDTO")
    TimeForCallUserDTO toTimeForCallUserDTO(TimeForCallUser timeForCallUser);

    @Named(value = "timeForCallWithUserDTO")
    TimeForCallUser toTimeForCallUserEntity(TimeForCallWithUserDTO time);

    @IterableMapping(qualifiedByName = "timeForCallUserDTO")
    List<TimeForCallUserDTO> mapToDTO(List<TimeForCallUser> timeForCallUserList);

    @IterableMapping(qualifiedByName = "timeForCallUser")
    List<TimeForCallUser> map(List<TimeForCallUserDTO> timeForCallUserDTOList);

    @IterableMapping(qualifiedByName = "timeForCallWithUserDTO")
    List<TimeForCallUser> mapToEntity(List<TimeForCallWithUserDTO> time);
}
