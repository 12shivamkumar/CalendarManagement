package org.example.CalendarManagement.thriftclients.implementation;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MeetingServiceClientImplTest {
    @Mock
    ClientController clientController;

    @InjectMocks
    MeetingServiceClientImpl meetingServiceClient;

    @Test
    void cancelMeetingForRemovedEmployeeSuccess() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.cancelMeetingOfRemovedEmployee(Mockito.anyString())).thenReturn(true);
        boolean response = meetingServiceClient.cancelMeetingForRemovedEmployee("xyz-12");
        assertTrue(response);
    }

    @Test
    void cancelMeetingForRemovedEmployeeFail() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.cancelMeetingOfRemovedEmployee(Mockito.anyString())).thenThrow(TException.class);
        Assertions.assertThrows(RuntimeException.class ,() -> meetingServiceClient.cancelMeetingForRemovedEmployee("xyz-12"));
    }

    @Test
    void updateStatusForRemovedEmployeeSuccess() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.updateStatusOfRemovedEmployee(Mockito.anyString())).thenReturn(true);
        boolean response = meetingServiceClient.updateStatusForRemovedEmployee("xyz-12");
        assertTrue(response);
    }

    @Test
    void updateStatusForRemovedEmployeeFail() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.updateStatusOfRemovedEmployee(Mockito.anyString())).thenThrow(TException.class);
        Assertions.assertThrows(RuntimeException.class , ()->meetingServiceClient.updateStatusForRemovedEmployee("xyz-12"));
    }

    @Test
    void checkEmployeeAvailabilitySuccess() throws TException {
        List<String> listOfEmployeeId = Arrays.asList("xyz-11" , "xyz-12");
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);
        EmployeeAvailabilityDataRequest employeeAvailabilityDataRequest = new EmployeeAvailabilityDataRequest(listOfEmployeeId,startTime,endTime,date);
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.checkEmployeeAvailability(Mockito.any(EmployeeAvailabilityDataRequest.class))).thenReturn(Collections.emptyList());
        List<String> listOfBusyEmployee = meetingServiceClient.checkEmployeeAvailability(employeeAvailabilityDataRequest);
        assertTrue(listOfBusyEmployee.size()==0);
    }

    @Test
    void checkEmployeeAvailabilityFailed() throws TException {
        List<String> listOfEmployeeId = Arrays.asList("xyz-11" , "xyz-12");
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);
        EmployeeAvailabilityDataRequest employeeAvailabilityDataRequest = new EmployeeAvailabilityDataRequest(listOfEmployeeId,startTime,endTime,date);
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.checkEmployeeAvailability(Mockito.any(EmployeeAvailabilityDataRequest.class))).thenThrow(TException.class);
        Assertions.assertThrows(RuntimeException.class,() -> meetingServiceClient.checkEmployeeAvailability(employeeAvailabilityDataRequest));
    }

    @Test
    void findFreeMeetingRoomSuccess() throws TException {
        List<Integer> listOfRoomId = Arrays.asList(1,2,3);
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);

        FindFreeMeetingRoomDataRequest findFreeMeetingRoomDataRequest = new FindFreeMeetingRoomDataRequest(listOfRoomId,date,startTime,endTime);

        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.findFreeMeetingRoom(Mockito.any(FindFreeMeetingRoomDataRequest.class))).thenReturn(1);
        int roomId = meetingServiceClient.findFreeMeetingRoom(findFreeMeetingRoomDataRequest);
        assertEquals(1,roomId);
    }

    @Test
    void findFreeMeetingRoomFail() throws TException {
        List<Integer> listOfRoomId = Arrays.asList(1,2,3);
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);

        FindFreeMeetingRoomDataRequest findFreeMeetingRoomDataRequest = new FindFreeMeetingRoomDataRequest(listOfRoomId,date,startTime,endTime);

        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.findFreeMeetingRoom(Mockito.any(FindFreeMeetingRoomDataRequest.class))).thenThrow(TException.class);
        Assertions.assertThrows(RuntimeException.class, ()->meetingServiceClient.findFreeMeetingRoom(findFreeMeetingRoomDataRequest));
    }

    @Test
    void meetingRoomAvailableSuccess() throws TException {
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);

        MeetingRoomAvailableDataRequest meetingRoomAvailableDataRequest = new MeetingRoomAvailableDataRequest(1,date,startTime,endTime);

        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.meetingRoomAvailable(Mockito.any(MeetingRoomAvailableDataRequest.class))).thenReturn(true);
        boolean response =  meetingServiceClient.meetingRoomAvailable(meetingRoomAvailableDataRequest);
        assertTrue(response);
    }

    @Test
    void meetingRoomAvailableFail() throws TException {
        Date date = new Date(26,8,2022);
        Time startTime = new Time(16,00,00);
        Time endTime = new Time(17,00,00);

        MeetingRoomAvailableDataRequest meetingRoomAvailableDataRequest = new MeetingRoomAvailableDataRequest(1,date,startTime,endTime);

        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.meetingRoomAvailable(Mockito.any(MeetingRoomAvailableDataRequest.class))).thenThrow(TException.class);
        Assertions.assertThrows(RuntimeException.class , () ->meetingServiceClient.meetingRoomAvailable(meetingRoomAvailableDataRequest));
    }

    @Test
    void isAliveTestSuccess() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.isAlive()).thenReturn("alive");
        String response =  meetingServiceClient.isAlive();
       assertEquals("alive",response);
    }

    @Test
    void isAliveTestFails() throws TException {
        MeetingSvc.Client clientMock = Mockito.mock(MeetingSvc.Client.class);
        Mockito.when(clientController.getClient()).thenReturn(clientMock);
        Mockito.when(clientMock.isAlive()).thenThrow(TException.class);
        assertThrows(RuntimeException.class , ()-> meetingServiceClient.isAlive());
    }
}