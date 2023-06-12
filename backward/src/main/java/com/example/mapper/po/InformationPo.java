package com.example.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "information")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double soilMoisture;//土壤水分
    private Double soilTemperature;//土壤温度
    private Double ph;
    private Double airTemperature;
    private Double airHumidity;
    private Double lightIntensity;
    private LocalDateTime createTime;
}
