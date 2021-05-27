package by.exadel.internship.mapper;

import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.dto.form.FormInfoDTO;
import by.exadel.internship.dto.form.FormRegisterDTO;
import by.exadel.internship.entity.Form;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormMapper {

    @Named(value = "form")
    FormFullDTO toFormDto(Form form);

    Form toFormEntity(FormFullDTO formDTO);

    FormRegisterDTO toFormRegDTO(Form form);

    Form toFormEntity(FormRegisterDTO formRegisterDTO);

    @IterableMapping(qualifiedByName = "form")
    List<FormFullDTO> map(List<Form> forms);

    @Named(value = "formInfo")
    FormInfoDTO toFormInfoDTO(Form form);

    @IterableMapping(qualifiedByName = "formInfo")
    List<FormInfoDTO> mapToInfoDto(List<Form> forms);

}