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
public class TeacherUpdateDTO extends BaseDTO {

    @Size(min = 6)
    private int ssn;

    @Size(min = 2, max = 255)
    private String firstname;

    @Size(min = 2, max = 255)
    private String lastname;

}
