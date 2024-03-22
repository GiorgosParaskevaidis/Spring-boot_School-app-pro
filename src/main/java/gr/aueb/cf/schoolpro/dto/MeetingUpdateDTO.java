package gr.aueb.cf.schoolpro.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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
public class MeetingUpdateDTO extends BaseDTO{

    @Size(min = 2, max = 45)
    private String meetingRoom;

    @NotNull
    @Future
    private Date meetingDate;

}
