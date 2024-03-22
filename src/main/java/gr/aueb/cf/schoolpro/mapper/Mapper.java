package gr.aueb.cf.schoolpro.mapper;

import gr.aueb.cf.schoolpro.dto.*;
import gr.aueb.cf.schoolpro.model.*;

public class Mapper {

    private Mapper() {}

    public static User mapToUser(UserInsertDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword(), dto.getRole());
    }

    public static User mapToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole());
    }

    public static UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public static City mapToCity(CityInsertDTO dto) {
        return new City(null, dto.getCityName());
    }

    public static City mapToCity(CityUpdateDTO dto) {
        return new City(dto.getCityName());
    }

    public static CityReadOnlyDTO mapToCityReadOnlyDTO(City city) {
        return new CityReadOnlyDTO(city.getId(), city.getCityName());
    }

    public static Speciality mapToSpeciality(SpecialityInsertDTO dto) {
        return new Speciality(dto.getSpeciality());
    }

    public static Speciality mapToSpeciality(SpecialityUpdateDTO dto) {
        return new Speciality(dto.getSpeciality());
    }

    public static SpecialityReadOnlyDTO mapToSpecialityReadOnlyDTO(Speciality speciality) {
        return new SpecialityReadOnlyDTO(speciality.getId(), speciality.getSpecialty());
    }

    public static Teacher mapToTeacher(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getSsn(), dto.getFirstname(), dto.getLastname());
    }

    public static Teacher mapToTeacher(TeacherUpdateDTO dto) {
        return new Teacher(dto.getSsn(), dto.getFirstname(), dto.getLastname());
    }

    public static TeacherReadOnlyDTO mapToTeacherReadOnlyDTO(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getSsn(), teacher.getFirstname(), teacher.getLastname());
    }

    public static Student mapToStudent(StudentInsertDTO dto) {
        return new Student(null, dto.getFirstname(), dto.getLastname(), dto.getGender(), dto.getBirthDate());
    }

    public static Student mapToStudent(StudentUpdateDTO dto) {
        return new Student(dto.getFirstname(), dto.getLastname(), dto.getGender(), dto.getBirthDate());
    }

    public static StudentReadOnlyDTO mapToStudentReadOnlyDTO(Student student) {
        return new StudentReadOnlyDTO(student.getId(), student.getFirstname(), student.getLastname(), student.getGender(), student.getBirthDate());
    }

    public static Meeting mapToMeeting(MeetingInsertDTO dto) {
        return new Meeting(dto.getMeetingRoom(), dto.getMeetingDate());
    }

    public static Meeting mapToMeeting(MeetingUpdateDTO dto) {
        return new Meeting(dto.getMeetingRoom(), dto.getMeetingDate());
    }

    public static MeetingReadOnlyDTO mapToMeetingReadOnlyDTO(Meeting meeting) {
        return new MeetingReadOnlyDTO(meeting.getMeetingRoom(), meeting.getMeetingDate());
    }
}
