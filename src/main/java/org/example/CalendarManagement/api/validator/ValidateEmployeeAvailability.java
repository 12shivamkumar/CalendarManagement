package org.example.CalendarManagement.api.validator;

import org.example.CalendarManagement.api.Response;
import org.example.CalendarManagement.thriftclients.interfaces.MeetingServiceClient;
import org.example.CalendarThriftConfiguration.EmployeeAvailabilityDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateEmployeeAvailability
{
    @Autowired
    MeetingServiceClient meetingServiceClient;

    public ValidateResponse checkEmployeeAvailability(EmployeeAvailabilityDataRequest employeeAvailabilityDataRequest)
    {
        ValidateResponse response = null;

        List<String> employeesNotAvailable = meetingServiceClient.checkEmployeeAvailability(employeeAvailabilityDataRequest);
        if(employeesNotAvailable.size()>0){
            response = new ValidateResponse(employeesNotAvailable+"", false);
            return response;
        }
       return new ValidateResponse(null, true);
    }
}
