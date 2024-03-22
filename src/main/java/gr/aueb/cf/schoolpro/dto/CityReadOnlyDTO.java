package gr.aueb.cf.schoolpro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityReadOnlyDTO extends BaseDTO {

    private String cityName;

    public CityReadOnlyDTO(Long id, String cityName) {
        setId(id);
        this.cityName = cityName;
    }
}
