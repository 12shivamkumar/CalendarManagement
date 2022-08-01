package org.example.CalendarManagement.thriftclients.interfaces;

import org.example.CalendarThriftConfiguration.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeetingServiceClient {

    boolean cancelMeetingForRemovedEmployee(String employeeId);

    boolean updateStatusForRemovedEmployee(String employeeId);

    List<String> checkEmployeeAvailability(EmployeeAvailabilityDataRequest employeeAvailabilityDataRequest);

    int addMeetingDetails(MeetingDetails meetingDetails);

    int findFreeMeetingRoom(FindFreeMeetingRoomDataRequest findFreeMeetingRoomDataRequest);

    boolean meetingRoomAvailable(MeetingRoomAvailableDataRequest meetingRoomAvailableDataRequest);

    List<EmployeeMeetingDetails> getEmployeeMeetingDetails(String employeeId, Date customDate);


    String isAlive();

}
