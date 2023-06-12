package com.example.dao;

import com.example.dao.bo.Information;
import com.example.mapper.InformationPoMapper;
import com.example.mapper.po.InformationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;

@Repository
@RefreshScope
public class InformationDao {
    private InformationPoMapper informationPoMapper;

    @Autowired
    public InformationDao(InformationPoMapper informationPoMapper){
        this.informationPoMapper = informationPoMapper;
    }

    public void saveInformation(Information information){
        InformationPo informationPo = InformationPo.builder().soilTemperature(information.getSoilTemperature()).soilMoisture(information.getSoilMoisture())
                .ph(information.getPh()).airTemperature(information.getAirTemperature()).airHumidity(information.getAirHumidity()).lightIntensity(information.getLightIntensity())
                .createTime(information.getCreateTime()).build();
        informationPoMapper.save(informationPo);
    }
}
