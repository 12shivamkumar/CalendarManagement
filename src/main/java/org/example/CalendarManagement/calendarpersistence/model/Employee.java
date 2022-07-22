package org.example.CalendarManagement.calendarpersistence.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
public class Employee {

    @Id
    private String id;

    @Column
    @NotNull
    private String name;

    @Column(name = "office_id")
    @NotNull
    private int officeId;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdDateTime;

    @Column(name = "auto_update_time")
    private LocalDateTime autoDateTime;


    public Employee(){}

    public Employee(String employeeId, String name, int officeId, String email) {
        this.id = employeeId;
        this.name = name;
        this.officeId = officeId;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Employee employee = (Employee) o;

        return this.id.equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOfficeId() {
        return officeId;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

   /* public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getAutoDateTime() {
        return autoDateTime;
    }*/

    /*@Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", officeId=" + officeId +
                ", email='" + email + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdDateTime=" + createdDateTime +
                '}';
    }*/
}