package by.exadel.internship.mapper;

import by.exadel.internship.dto.form.FormFullDTO;
import by.exadel.internship.entity.Form;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleFormMapperTest {
    private FormMapper mapper
            = Mappers.getMapper(FormMapper.class);

    @Test
    public void givenEntityToDTO_whenMaps_thenCorrect() {
        Form form = new Form();
        form.setFirstName("Nikita");
        form.setLastName("Bogush");
        FormFullDTO formFullDTO = mapper.toFormDto(form);

        assertEquals(form.getCity(), formFullDTO.getCity());
        assertEquals(form.getCountry(), formFullDTO.getCountry());
    }

    @Test
    public void givenDTOtoEntity_whenMaps_thenCorrect() {
        FormFullDTO formFullDTO = new FormFullDTO();
        formFullDTO.setEmail("testEmail");
        formFullDTO.setEducation("testEducation");
        Form form = mapper.toFormEntity(formFullDTO);

        assertEquals(formFullDTO.getEducation(), form.getEducation());
        assertEquals(formFullDTO.getEmail(), form.getEmail());
    }
}