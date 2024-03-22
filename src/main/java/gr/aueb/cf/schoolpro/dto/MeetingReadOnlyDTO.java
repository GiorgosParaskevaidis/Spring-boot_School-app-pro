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
public class MeetingReadOnlyDTO extends BaseDTO {

    private String meetingRoom;
    private Date meetingDate;

    public MeetingReadOnlyDTO(Long id, String meetingRoom, Date meetingDate) {
        setId(id);
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }
}
