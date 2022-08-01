package org.example.CalendarManagement.api.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
@Component
public class ValidateMeetingDateTime {

    public ValidateResponse checkMeetingDateTime(LocalDate date , LocalTime startTime)
    {

        if(date.isEqual(LocalDate.now()))
        {
           if(startTime.getHour()>=LocalTime.now().getHour()){
               return new ValidateResponse("meeting can be scheduled" , true);
           }
           else{
                return new ValidateResponse("invalid time for meeting meeting can't be scheduled in past" , false);
           }
        }
        else if (date.isAfter(LocalDate.now())) {
            return new ValidateResponse("meeting can be scheduled" , true);
        }
        else {
            return  new ValidateResponse("invalid date for meeting , meeting can't be scheduled in past" , false);
        }
    }

}


