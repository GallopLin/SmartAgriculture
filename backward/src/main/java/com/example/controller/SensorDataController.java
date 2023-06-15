package com.example.controller;

import com.example.model.ReturnObject;
import com.example.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value ="/", produces = "application/json;charset=UTF-8")
public class SensorDataController {
    private SensorDataService sensorDataService;
    @Autowired
    public SensorDataController(SensorDataService sensorDataService){
        this.sensorDataService = sensorDataService;
    }


    @GetMapping("/average")
    public ReturnObject getAverEveryDay(){
        return new ReturnObject(sensorDataService.findAverAllByEveryDay());
    }

    @GetMapping("/warning")
    public ReturnObject getWarnings(){
        return new ReturnObject(sensorDataService.findWarningsAllByEveryDay());
    }
}
