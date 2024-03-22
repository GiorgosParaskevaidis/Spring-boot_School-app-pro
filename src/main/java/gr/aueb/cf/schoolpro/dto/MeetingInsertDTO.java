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
public class MeetingInsertDTO {

    @Size(max = 45, min = 2, message = "Error in room length")
    private String meetingRoom;

    @Future
    private Date meetingDate;
}
