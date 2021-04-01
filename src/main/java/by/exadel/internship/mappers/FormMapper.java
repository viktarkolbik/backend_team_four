package by.exadel.internship.mappers;

import by.exadel.internship.dto.FormDTO;
import by.exadel.internship.entity.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FormMapper {

    FormDTO toFormDto(FormEntity formEntity);
}
