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
public class UserReadOnlyDTO extends BaseDTO {

    private String username;
    private String password;
    private String role;

    public UserReadOnlyDTO(Long id, String username, String password, String role) {
        setId(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
