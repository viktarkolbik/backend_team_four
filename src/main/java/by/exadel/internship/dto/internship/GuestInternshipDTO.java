package by.exadel.internship.dto.internship;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GuestInternshipDTO extends BaseInternshipDTO {

    @Size(max=250)
    private String techSkills;

    @Size(max=150)
    private String description;

    @Size(max=150)
    private String requirements;

}
