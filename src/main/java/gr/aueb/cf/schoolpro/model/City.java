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
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
public class City extends AbstractEntity {

    @NonNull
    @Column(name = "city", length = 45, nullable = false, unique = true)
    private String cityName;

    public City(Long id, String cityName) {
        super(id);
        this.cityName = cityName;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    @OneToMany(mappedBy = "city")
    @Getter(AccessLevel.PROTECTED)
    private Set<Student> students = new HashSet<>();


    public Set<Student> getAllStudents() {
       return Collections.unmodifiableSet(students);
    }

    public void addStudents(Student student) {
        students.add(student);
        student.setCity(this);
    }
}
