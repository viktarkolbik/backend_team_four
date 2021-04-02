package by.exadel.internship.mappers;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.entity.FormEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FormMapper {

    FormFullDTO toFormDto(FormEntity formEntity);

    FormEntity toFormEntity(FormFullDTO formDTO);
}
