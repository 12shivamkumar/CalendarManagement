package org.example.CalendarManagement.calendarservice.implementation;

import org.example.CalendarManagement.api.validator.ValidateResponse;
import org.example.CalendarManagement.calendarpersistence.model.Employee;
import org.example.CalendarManagement.calendarpersistence.repository.EmployeeRepository;
import org.example.CalendarManagement.calendarpersistence.repository.MeetingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    MeetingRepository meetingRepository;
    @InjectMocks
    EmployeeService employeeService;


    @Test
    public void addEmployeeTest_employeeSavedSuccessfully() {
        String employeeId = "CAP-1";
        String email = "s@cap.com";
        String name = "xyz";
        int officeID = 101;
        Employee employeeWithValidArguments = new Employee(employeeId, name, officeID, email);
        Mockito.when(employeeRepository.save(employeeWithValidArguments)).thenReturn(employeeWithValidArguments);
        assertNotNull(employeeService.addEmployee(employeeWithValidArguments));
    }

    @Test
    public void addEmployeeTest_employeeSaveFailed() {
        String employeeId = "CAP-1";
        String name = "xyz";
        int officeID = 101;
        boolean thrown = false;
        Employee employeeWithInvalidArguments = new Employee(employeeId, name, officeID, null);
        DataAccessException dataAccessException = new DataAccessException("Data cannot be accessed") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        };
        Mockito.when(employeeRepository.save(employeeWithInvalidArguments)).thenThrow(dataAccessException);
        Assertions.assertThrows(DataAccessException.class, () -> employeeService.addEmployee(employeeWithInvalidArguments));
    }

    @Test
    public void removeEmployeeByIdTest_employeeRemovedSuccessfully() {
        String id = "XYZ-123";

        Mockito.when(employeeRepository.deletedById(id)).
                thenReturn(new Employee(id, "shivam", 1, "shivam@xyz.com"));

        Employee deletedEmployee = employeeService.removeEmployeeById(id);

        assertNotNull(deletedEmployee);

        assertEquals(id, deletedEmployee.getId());
    }

    @Test
    public void removeEmployeeByIdTest_employeeRemoveFailed() {
        String id = "XYZ-123";
        DataAccessException dataAccessException = new DataAccessException("Data cannot be accessed") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        };

        Mockito.when(employeeRepository.deletedById(id)).thenThrow(dataAccessException);

        Assertions.assertThrows(DataAccessException.class, () -> employeeService.removeEmployeeById(id));

    }

    @Test
    public void removeEmployeeByEmailTest_removeEmployeeSuccess() {
        String email = "shivam@xyz.com";

        Mockito.when(employeeRepository.deleteByEmail(email)).
                thenReturn(new Employee("xyz-123", "Shivam", 1, email));

        Employee deletedEmployee = employeeService.removeEmployeeByEmail(email);

        assertNotNull(deletedEmployee);

        assertEquals(email, deletedEmployee.getEmail());
    }

    @Test
    public void removeEmployeeByEmailTest_employeeRemoveSuccess() {
        String email = "shivam@xyz.com";
        DataAccessException dataAccessException = new DataAccessException("Data cannot be accessed") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        };

        Mockito.when(employeeRepository.deleteByEmail(email)).thenThrow(dataAccessException);

        Assertions.assertThrows(DataAccessException.class, () -> employeeService.removeEmployeeByEmail(email));
    }


}