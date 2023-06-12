package com.example.service;

import com.example.dao.InformationDao;
import com.example.dao.bo.Information;
import com.example.mapper.po.InformationPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataService {
    private InformationDao informationDao;
    @Autowired
    public SensorDataService(InformationDao informationDao){
        this.informationDao = informationDao;
    }

    public void saveInformation(Information information){
        InformationPo informationPo = InformationPo.builder().build();
    }

    public List<Object> findAverAllByEveryDay(){
        List<Object> ret = informationDao.findAverAllByEveryDay();
        return ret;
    }

    public List<Object> findWarningsAllByEveryDay(){
        List<Object> ret = informationDao.findWarningsAllByEveryDay();
        return ret;
    }
}
