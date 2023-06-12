package com.example.sensor;

import java.util.Random;

public class TemperatureSensor {
    private Random random;

    public TemperatureSensor() {
        random = new Random();
    }

    public double generateTemperature(double max, double min) {
        // 模拟温度传感器数据
        Random random = new Random();
        return Math.round((random.nextDouble() * (max - min) + min) * 100) / 100.0;
    }
}
