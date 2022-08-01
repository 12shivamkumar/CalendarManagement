package org.example.CalendarManagement.calendarservice.implementation;

import org.example.CalendarManagement.calendarpersistence.model.Employee;
import org.example.CalendarManagement.calendarpersistence.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

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
        DataAccessException dataAccessException = new DataAccessException("data cannot be accessed") {
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

        Mockito.when(employeeRepository.findById(id)).
                thenReturn(Optional.of(new Employee(id, "shivam", 1, "shivam@xyz.com")));

        Employee deletedEmployee = employeeService.removeEmployeeById(id);

        assertNotNull(deletedEmployee);

        assertEquals(id, deletedEmployee.getId());
    }

    @Test
    public void removeEmployeeByIdTest_employeeRemoveFailed() {
        String id = "XYZ-123";
        DataAccessException dataAccessException = new DataAccessException("data cannot be accessed") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        };

        Mockito.when(employeeRepository.findById(id)).thenThrow(dataAccessException);

        Assertions.assertThrows(DataAccessException.class, () -> employeeService.removeEmployeeById(id));

    }


}