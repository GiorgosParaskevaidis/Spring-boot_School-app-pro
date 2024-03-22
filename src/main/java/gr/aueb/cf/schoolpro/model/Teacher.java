package gr.aueb.cf.schoolpro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Teacher extends AbstractEntity {

    @Column(name = "ssn", nullable = false, unique = true)
    private int ssn;

    @Column(name = "firstname", length = 45, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 45, nullable = false)
    private String lastname;

    public Teacher(Long id, int ssn, String firstname, String lastname) {
        super(id);
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Teacher(int ssn, String firstname, String lastname) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    @OneToMany(mappedBy = "teacher")
    @Getter(AccessLevel.PROTECTED)
    private Set<Meeting> meetings = new HashSet<>();


    public Set<Meeting> getAllMeetings() {
        return Collections.unmodifiableSet(meetings);
    }

    public void addMeetings(Meeting meeting) {
        meetings.add(meeting);
        meeting.setTeacher(this);
    }
}
