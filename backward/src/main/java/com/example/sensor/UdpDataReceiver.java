package com.example.sensor;

import com.example.dao.InformationDao;
import com.example.dao.bo.Information;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Component
public class UdpDataReceiver {

    private final SensorDataHandler sensorDataHandler;

    private final InformationDao informationDao;

    @Autowired
    public UdpDataReceiver(SensorDataHandler sensorDataHandler, InformationDao informationDao) {
        this.sensorDataHandler = sensorDataHandler;
        this.informationDao = informationDao;
    }

    public void startUdpServer(int udpPort) {
        new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket(udpPort);
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                    socket.receive(receivePacket);
                    String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
                    Information information = mapper.readValue(receivedData, Information.class);
                    informationDao.saveInformation(information);
                    sendData(receivedData);
//                    System.out.println(receivedData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void sendData(String information) {
        try {
            sensorDataHandler.sendSensorDataToAllSessions(information);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
