package com.example.dao.bo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Information {
    private Double soilMoisture;//土壤水分
    private Double soilTemperature;//土壤温度
    private Double ph;
    private Double airTemperature;
    private Double airHumidity;
    private Double lightIntensity;
}
