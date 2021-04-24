package by.exadel.internship.mapper;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.entity.Form;
import by.exadel.internship.entity.location.City;
import by.exadel.internship.entity.location.Country;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleFormMapperTest {
    private FormMapper mapper
            = Mappers.getMapper(FormMapper.class);

    @Test
    public void givenEntityToDTO_whenMaps_thenCorrect() {
        Form form = new Form();
        City city  = new City("Minsk");
        form.setCity(city);
        Country country = new Country("Belarus");
        form.setCountry(country);
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