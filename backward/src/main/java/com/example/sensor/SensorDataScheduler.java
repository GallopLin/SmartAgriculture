package com.example.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorDataScheduler {

    private final TemperatureSensor temperatureSensor;

    @Autowired
    public SensorDataScheduler(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @Scheduled(fixedRate = 5000) // 每5秒调度一次
    public void sendSensorData() {
        temperatureSensor.startSensor();
    }
}