package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolpro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Meeting;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.repository.MeetingRepository;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MeetingServiceImpl implements IMeetingService{

    private MeetingRepository meetingRepository;

    @Transactional
    @Override
    public Meeting insertMeeting(MeetingInsertDTO meetingInsertDTO) throws Exception {
        Meeting meeting = null;

        try {
            meeting = meetingRepository.save(Mapper.mapToMeeting(meetingInsertDTO));
            if (meeting.getStudent() == null || meeting.getTeacher() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert Success for meeting with id:" + meeting.getTeacher() + meeting.getStudent());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return meeting;
    }

    @Transactional
    @Override
    public Meeting updateMeeting(MeetingUpdateDTO meetingUpdateDTO) throws EntityNotFoundException {
        Optional<Meeting> meeting = null;
        Meeting updatedMeeting;

        try {
            meeting = Optional.ofNullable(meetingRepository.findById(meetingUpdateDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException(Meeting.class, meetingUpdateDTO.getId())));


            updatedMeeting = meetingRepository.save(Mapper.mapToMeeting(meetingUpdateDTO));
            log.info("Teacher with id:" + updatedMeeting.getStudent() + updatedMeeting.getTeacher() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedMeeting;
    }
}
