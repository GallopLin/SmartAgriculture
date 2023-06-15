package com.example.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;

@Service
@Component
public class SensorDataHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Autowired
    public SensorDataHandler() throws SocketException {
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessions.add(session);
        // 在连接建立后开始发送传感器数据
    }

    public void sendSensorDataToAllSessions(String information) throws IOException {
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(information));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

}
