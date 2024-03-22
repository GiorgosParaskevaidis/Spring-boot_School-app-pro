package gr.aueb.cf.schoolpro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "meetings")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Meeting {

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @NonNull
    @Column(name = "meeting_room", length = 45, nullable = false)
    private String meetingRoom;

    @NonNull
    @Column(name = "meeting_date")
    private Date meetingDate;

    public Meeting(String meetingRoom, Date meetingDate) {
        this.meetingRoom = meetingRoom;
        this.meetingDate = meetingDate;
    }
}
