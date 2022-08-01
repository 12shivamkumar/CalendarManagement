package org.example.CalendarManagement.api.validator;

import org.example.CalendarManagement.api.request.AddEmployeeDataRequest;
import org.example.CalendarManagement.calendarpersistence.model.Office;
import org.example.CalendarManagement.calendarpersistence.repository.OfficeRepository;
//import org.example.CalendarManagement.calendarservice.implementation.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateOfficeId {

    @Autowired
    private OfficeRepository officeRepository;

    public ValidateResponse checkOfficeId(int officeId)
    {
        ValidateResponse validateResponse = null;

        Optional<Office> responseFromDb = officeRepository.findById(officeId);

        if(responseFromDb.isPresent())
        {
            validateResponse = new ValidateResponse("office information is present" , true);

        }
        else
        {
            validateResponse = new ValidateResponse("office information is not present" , false);
        }
        return validateResponse;
    }


}
