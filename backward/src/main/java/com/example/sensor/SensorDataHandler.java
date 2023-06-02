package com.example.sensor;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Random;

public class SensorDataHandler extends TextWebSocketHandler {

    private final Random random = new Random();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 在这里处理传入的消息（如果有需要）
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 在连接建立后开始发送传感器数据
        while (session.isOpen()) {
            double temperature = generateRandomTemperature();
            session.sendMessage(new TextMessage(Double.toString(temperature)));
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
