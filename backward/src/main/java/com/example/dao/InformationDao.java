package com.example.dao;

import com.example.dao.bo.Information;
import com.example.mapper.InformationPoMapper;
import com.example.mapper.po.InformationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Object> findAverAllByEveryDay(){
        List<Object> ret = informationPoMapper.findAverAllByEveryDay();
        return ret;
    }

    public List<Object> findWarningsAllByEveryDay(){
        List<Object> ret = informationPoMapper.findWarningsAllByEveryDay();
        return ret;
    }
}
