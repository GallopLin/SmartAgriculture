package com.example.sensor;


import com.example.model.Information;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class Sensor {
    private Random random;

    public Sensor() {

    }

    public double generateTemperature(double max, double min) {
        // 模拟温度传感器数据
        Random random = new Random();
        return Math.round((random.nextDouble() * (max - min) + min) * 100) / 100.0;
    }

    public String generateData() throws JsonProcessingException {
        double soilMoisture = generateTemperature(50.0, 60.0);
        double soilTemperature = generateTemperature(20.0, 23.0);
        double ph = generateTemperature(7.0, 7.5);
        double airTemperature = generateTemperature(20.0, 23.0);
        double airHumidity = generateTemperature(50.0, 60.0);
        double lightIntensity = generateTemperature(3000.0, 3100.0);
        LocalDateTime createTime = LocalDateTime.now();
        Information information = new Information(soilMoisture,soilTemperature,ph,airTemperature,airHumidity,lightIntensity,createTime);
        ObjectMapper mapper = new ObjectMapper();
        // 构造数据包
        String sensorData = mapper.writeValueAsString(information);
        return sensorData;
    }


}
