package org.example.CalendarManagement.thriftclients.implementation;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.example.CalendarManagement.calendarfacade.EmployeeFacade;
import org.example.CalendarManagement.thriftclients.interfaces.MeetingServiceClient;
import org.example.CalendarThriftConfiguration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("!test")
public class MeetingServiceClientImpl implements MeetingServiceClient
{
    @Autowired
    ClientController clientController;

    Logger logger = LoggerFactory.getLogger(EmployeeFacade.class);
    @Override
    public boolean cancelMeetingForRemovedEmployee(String employeeId){
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            boolean thriftResponse = client.cancelMeetingOfRemovedEmployee(employeeId);
            return  thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
        finally {
            clientController.closeConnection();
        }
    }

    @Override
    public boolean updateStatusForRemovedEmployee(String employeeId){
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            boolean thriftResponse = client.updateStatusOfRemovedEmployee(employeeId);
            return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public List<String> checkEmployeeAvailability(EmployeeAvailabilityDataRequest employeeAvailabilityDataRequest) {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            List<String> listOfEmployeeNotAvailable = client.checkEmployeeAvailability(employeeAvailabilityDataRequest);
            return listOfEmployeeNotAvailable;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public int addMeetingDetails(MeetingDetails meetingDetails) {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            int thriftResponse = client.addMeetingDetails(meetingDetails);

            return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public int findFreeMeetingRoom(FindFreeMeetingRoomDataRequest findFreeMeetingRoomDataRequest) {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            int thriftResponse = client.findFreeMeetingRoom(findFreeMeetingRoomDataRequest);
           return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public boolean meetingRoomAvailable(MeetingRoomAvailableDataRequest meetingRoomAvailableDataRequest) {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            boolean thriftResponse = client.meetingRoomAvailable(meetingRoomAvailableDataRequest);
            return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public List<EmployeeMeetingDetails> getEmployeeMeetingDetails(String employeeId, Date customDate) {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            List<EmployeeMeetingDetails> thriftResponse = client.getEmployeeMeetingDetails(employeeId,customDate);
          return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

    @Override
    public String isAlive() {
        try
        {
            MeetingSvc.Client client = clientController.getClient();
            String thriftResponse = client.isAlive();
         return thriftResponse;
        }catch (TException exception)
        {
            logger.error(exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }finally {
            clientController.closeConnection();
        }
    }

}


