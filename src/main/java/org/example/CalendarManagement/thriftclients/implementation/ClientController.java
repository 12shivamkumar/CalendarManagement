package org.example.CalendarManagement.thriftclients.implementation;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.example.CalendarThriftConfiguration.MeetingSvc;
import org.springframework.stereotype.Component;

@Component
public class ClientController
{
    TTransport transport = new TSocket("localhost",9090) ;

    public  MeetingSvc.Client getClient() throws TTransportException {

         transport.open();

         TProtocol protocol = new TBinaryProtocol(transport);

         MeetingSvc.Client client = new MeetingSvc.Client(protocol);

         return client;
    }

    public void closeConnection()
    {
        transport.close();
    }
}
