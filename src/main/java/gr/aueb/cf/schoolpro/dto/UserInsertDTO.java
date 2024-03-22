package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @Size(min = 8, max = 45, message = "Error in username length")
    private String username;

    @Size(min = 8, max = 255, message = "Error in password length")
    private String password;

    @Size(min = 2, max = 45, message = "Error in role length")
    private String role;
}
