package by.exadel.internship.mapper;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.dto.formDTO.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FormMapper {

    FormFullDTO toFormDto(Form form);

    Form toFormEntity(FormFullDTO formDTO);

    FormRegisterDTO toFormRegDTO(Form form);

    Form toFormEntity(FormRegisterDTO formRegisterDTO);


}
