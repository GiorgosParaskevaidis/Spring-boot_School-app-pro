package gr.aueb.cf.schoolpro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Speciality extends AbstractEntity {

    @NonNull
    @Column(name = "speciality", length = 45, nullable = false, unique = true)
    private String specialty;

    public Speciality(Long id, String speciality) {
        super(id);
        this.specialty = speciality;
    }

    public Speciality(String speciality) {
        this.specialty = speciality;
    }

    @OneToMany(mappedBy = "speciality")
    @Getter(AccessLevel.PROTECTED)
    private Set<Teacher> teachers = new HashSet<>();


    public Set<Teacher> getAllTeachers() {
         return Collections.unmodifiableSet(teachers);
    }

    public void addTeachers(Teacher teacher) {
        teachers.add(teacher);
        teacher.setSpeciality(this);
    }
}
