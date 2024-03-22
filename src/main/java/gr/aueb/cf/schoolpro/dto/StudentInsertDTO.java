package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentInsertDTO {

    @Size(min = 2, max = 45, message = "Error in firstname length")
    private String firstname;

    @Size(min = 2, max = 45, message = "Error in lastname length")
    private String lastname;

    @Size(min = 1, max = 1, message = "Error in gender length")
    private String gender;

    @Past
    private Date birthDate;
}
