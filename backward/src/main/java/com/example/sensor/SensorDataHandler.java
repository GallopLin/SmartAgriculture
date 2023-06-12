package com.example.sensor;

import com.example.dao.InformationDao;
import com.example.dao.bo.Information;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;
@Service
@Component
public class SensorDataHandler extends TextWebSocketHandler {

    private final Random random = new Random();
    private final Integer PORT = 8888;
    private DatagramSocket socket = null;


    private InformationDao informationDao;
    @Autowired
    public SensorDataHandler(InformationDao informationDao){
        this.informationDao = informationDao;
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 在这里处理传入的消息（如果有需要）
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 在连接建立后开始发送传感器数据
        socket = new DatagramSocket(PORT);
        byte[] receiveBuffer = new byte[1024];
        while (session.isOpen()) {
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
            Information information = mapper.readValue(receivedData, Information.class);
//            informationDao.saveInformation(information);
            session.sendMessage(new TextMessage(receivedData));
            try {
                Thread.sleep(1000); // 每秒发送一次数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
