package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentReadOnlyDTO extends BaseDTO {

    private String firstname;
    private String lastname;
    private String gender;
    private Date birthdate;

    public StudentReadOnlyDTO(Long id, String firstname, String lastname, String gender, Date birthdate) {
        setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
    }
}
