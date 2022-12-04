package com.proshore.battery.controller;

import com.proshore.battery.model.Battery;
import com.proshore.battery.service.BatteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "battery")
public class BatteryController {

    private final BatteryService batteryService;

    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping
    ResponseEntity<?> saveBatteries(@RequestBody List<Battery> batteriesList) {
        return new ResponseEntity<>(batteryService.save(batteriesList), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> getBatteriesListBetweenRange(@RequestParam long startPostalCode, @RequestParam long endPostalCode) {
        return new ResponseEntity<>(batteryService.getBatteryByPostCodeRange(startPostalCode, endPostalCode), HttpStatus.OK);
    }
}
