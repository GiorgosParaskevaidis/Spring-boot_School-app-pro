package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialityReadOnlyDTO extends BaseDTO {

    private String speciality;

    public SpecialityReadOnlyDTO(Long id, String speciality) {
        setId(id);
        this.speciality = speciality;
    }
}
