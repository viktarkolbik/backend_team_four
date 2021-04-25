package by.exadel.internship.mapper;

import by.exadel.internship.dto.TimeForCallDTO;
import by.exadel.internship.dto.TimeForCallUserDTO;
import by.exadel.internship.entity.TimeForCall;
import by.exadel.internship.entity.TimeForCallUser;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeForCallMapper {

    @Named(value = "timeForCall")
    TimeForCall toTimeForCallEntity(TimeForCallDTO timeForCallDTO);

    @Named(value = "timeForCallDTO")
    TimeForCallDTO toTimeForCallDTO(TimeForCall timeForCall);

    @IterableMapping(qualifiedByName = "timeForCallDTO")
    List<TimeForCallDTO> mapToDTO(List<TimeForCall> timeForCallList);


    @IterableMapping(qualifiedByName = "timeForCall")
    List<TimeForCall> map(List<TimeForCallDTO> timeForCallDTOList);
}
