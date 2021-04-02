package by.exadel.internship.team_four;

import by.exadel.internship.dto.formDTO.FormFullDTO;
import by.exadel.internship.entity.FormEntity;
import by.exadel.internship.mappers.FormMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleFormMapperTest {
    private FormMapper mapper
            = Mappers.getMapper(FormMapper.class);

    @Test
    public void givenEntityToDTO_whenMaps_thenCorrect() {
        FormEntity formEntity = new FormEntity();
        formEntity.setCity("minsk");
        formEntity.setCountry("Belarus");
        FormFullDTO formFullDTO = mapper.toFormDto(formEntity);

        assertEquals(formEntity.getCity(), formFullDTO.getCity());
        assertEquals(formEntity.getCountry(), formFullDTO.getCountry());
    }

    @Test
    public void givenDTOtoEntity_whenMaps_thenCorrect() {
        FormFullDTO formFullDTO = new FormFullDTO();
        formFullDTO.setEmail("testEmail");
        formFullDTO.setEducation("testEducation");
        FormEntity formEntity = mapper.toFormEntity(formFullDTO);

        assertEquals(formFullDTO.getEducation(), formEntity.getEducation());
        assertEquals(formFullDTO.getEmail(), formEntity.getEmail());
    }

}
