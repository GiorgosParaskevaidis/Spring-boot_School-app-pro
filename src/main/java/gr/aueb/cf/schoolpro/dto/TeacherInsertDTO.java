package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherInsertDTO {

    @Size(min = 6)
    private int ssn;

    @Size(min = 2, max = 255, message = "Error in firstname length")
    private String firstname;

    @Size(min = 2, max = 255, message = "Error in lastname length")
    private String lastname;

}
