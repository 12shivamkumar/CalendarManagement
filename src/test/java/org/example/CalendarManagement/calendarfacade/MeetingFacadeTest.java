package org.example.CalendarManagement.calendarfacade;

import org.example.CalendarManagement.api.Response;
import org.example.CalendarManagement.api.request.AddMeetingDataRequest;
import org.example.CalendarManagement.calendarpersistence.model.MeetingRoom;
import org.example.CalendarManagement.calendarpersistence.repository.EmployeeRepository;
import org.example.CalendarManagement.calendarpersistence.repository.MeetingRoomRepository;
import org.example.CalendarManagement.calendarservice.implementation.MeetingRoomService;
import org.example.CalendarManagement.thriftclients.interfaces.MeetingServiceClient;
import org.example.CalendarThriftConfiguration.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MeetingFacadeTest {
    @Mock
    MeetingRoomService meetingRoomService;
    @Mock
    MeetingServiceClient meetingServiceClient;
    @Mock
    MeetingRoomRepository meetingRoomRepository;
    @InjectMocks
    MeetingFacade meetingFacade;

    @Test
    public void saveMeetingDetails_meetingDetailsSavedSuccessfully()
    {
        AddMeetingDataRequest addMeetingDataRequest = new AddMeetingDataRequest.Builder(
                "abc-10",
                "daily sync up",
                "details",
                Arrays.asList("abc-12","abc-13","abc-14","abc-15"),
                LocalDate.of(2022,8,25),
                LocalTime.of(11,00,00),
                LocalTime.of(12,30,00)
        ).roomName(Optional.of("reon-dev")).build();

        Mockito.when(meetingServiceClient.addMeetingDetails(Mockito.any(MeetingDetails.class))).thenReturn(20128229);
        Response responseFromFacade = meetingFacade.scheduleMeeting(addMeetingDataRequest , 1);
        assertNotNull(responseFromFacade);
        Integer responseMeetingId = (Integer) responseFromFacade.getData();
        assertEquals(20128229 , responseMeetingId);
    }

    @Test
    public void saveMeetingDetails_meetingDetailsNotSavedSuccessfully()
    {
        AddMeetingDataRequest addMeetingDataRequest = new AddMeetingDataRequest.Builder(
                "abc-10",
                "daily sync up",
                "details",
                Arrays.asList("abc-12","abc-13","abc-14","abc-15"),
                LocalDate.of(2022,8,25),
                LocalTime.of(11,00,00),
                LocalTime.of(12,30,00)
        ).roomName(Optional.of("reon-dev")).build();
         Mockito.when(meetingServiceClient.addMeetingDetails(Mockito.any(MeetingDetails.class))).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class , () -> meetingFacade.scheduleMeeting(addMeetingDataRequest,1));
    }
}