package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO extends BaseDTO {

    @Size(min = 8, max = 45)
    private String username;

    @Size(min = 8, max = 255)
    private String password;

    @Size(min = 2, max = 45)
    private String role;

}
