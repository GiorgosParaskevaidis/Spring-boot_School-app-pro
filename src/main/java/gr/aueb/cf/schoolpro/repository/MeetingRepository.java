package gr.aueb.cf.schoolpro.repository;

import gr.aueb.cf.schoolpro.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByRoomStartingWith(String meetingRoom);
}
