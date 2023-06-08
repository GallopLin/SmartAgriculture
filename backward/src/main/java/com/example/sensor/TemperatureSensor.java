package com.example.sensor;

import java.util.Random;

public class TemperatureSensor {
    private Random random;

    public TemperatureSensor() {
        random = new Random();
    }

    public double generateTemperature() {
        // 模拟温度传感器数据
        double minTemperature = 20.0;
        double maxTemperature = 30.0;
        return minTemperature + (maxTemperature - minTemperature) * random.nextDouble();
    }
}
