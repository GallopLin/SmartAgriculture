package com.example.sensor;

import com.example.dao.bo.Information;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class SensorDataHandler extends TextWebSocketHandler {

    private final Random random = new Random();
    private final Integer PORT = 8888;
    private DatagramSocket socket = null;

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
            double temperature = Double.parseDouble(receivedData.toString());
//            double temperature = generateRandomTemperature();
            ObjectMapper mapper = new ObjectMapper();
            Information information = Information.builder().soilMoisture(temperature).soilTemperature(temperature+1)
                    .airTemperature(temperature+2).airHumidity(temperature+3).ph(temperature+4).lightIntensity(temperature+5).build();
            String json = mapper.writeValueAsString(information);
            session.sendMessage(new TextMessage(json));
            try {
                Thread.sleep(1000); // 每秒发送一次数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private double generateRandomTemperature() {
        // 在此处生成随机的温度值
        return random.nextDouble() * 100;
    }
}
