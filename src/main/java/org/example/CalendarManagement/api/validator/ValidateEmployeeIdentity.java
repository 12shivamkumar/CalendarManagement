package org.example.CalendarManagement.api.validator;

import org.example.CalendarManagement.calendarpersistence.model.Employee;
import org.example.CalendarManagement.calendarpersistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidateEmployeeIdentity {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ValidateResponse checkEmployeeId(String id)
    {
        ValidateResponse validateResponse = null;

        Optional<Employee> responseFromDb = employeeRepository.findById(id);

        if(responseFromDb.isPresent())
        {
            validateResponse = new ValidateResponse( "Employee Exists" , true);
        }
        else
        {
            validateResponse = new ValidateResponse("Employee does not exists", false);
        }
      return validateResponse;
    }

    public ValidateResponse checkEmployeeEmail(String email)
    {
        ValidateResponse validateResponse = null;

        Optional<Employee> responseFromDb = employeeRepository.findByEmail(email);

        if(responseFromDb.isPresent())
        {
            validateResponse = new ValidateResponse("Employee Exists" , true);
        }
        else
        {
            validateResponse = new ValidateResponse("Employee does not exists" , false);
        }

        return validateResponse;
    }

    public String isAlive()
    {
        return "";
    }
}
