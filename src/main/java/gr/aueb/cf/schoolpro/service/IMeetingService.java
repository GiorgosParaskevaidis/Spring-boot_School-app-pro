package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolpro.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolpro.model.Meeting;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IMeetingService {

    Meeting insertMeeting(MeetingInsertDTO meetingInsertDTO) throws Exception;
    Meeting updateMeeting(MeetingUpdateDTO meetingUpdateDTO) throws EntityNotFoundException;
}
