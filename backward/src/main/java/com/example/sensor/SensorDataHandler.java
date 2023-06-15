package com.example.sensor;

import com.example.dao.InformationDao;
import com.example.dao.bo.Information;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Component
public class SensorDataHandler extends TextWebSocketHandler {

    private final Random random = new Random();
    private final Integer PORT = 8888;
//    private DatagramSocket socket = null;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    private InformationDao informationDao;

    @Autowired
    public SensorDataHandler(InformationDao informationDao) throws SocketException {
        this.informationDao = informationDao;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessions.add(session);
        // 在连接建立后开始发送传感器数据
//        byte[] receiveBuffer = new byte[1024];
//        while (session.isOpen()) {
//            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
//            socket.receive(receivePacket);
//            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
//            ObjectMapper mapper = new ObjectMapper();
////            mapper.registerModule(new JavaTimeModule());
//            Information information = mapper.readValue(receivedData, Information.class);
//            informationDao.saveInformation(information);
//            session.sendMessage(new TextMessage(receivedData));
//            try {
//                Thread.sleep(10000); // 每秒发送一次数据
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void sendSensorDataToAllSessions(String information) throws IOException {
        for (WebSocketSession session : sessions) {
            System.out.println("sent");
            session.sendMessage(new TextMessage(information));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

}
