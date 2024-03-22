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
public class StudentUpdateDTO extends BaseDTO {

    @Size(min = 2, max = 45)
    private String firstname;

    @Size(min = 2, max = 45)
    private String lastname;

    @Size(min = 1, max = 1)
    private String gender;

    @Past
    private Date birthDate;

}
