package com.proshore.battery.service;

import com.proshore.battery.model.Battery;

import java.util.List;
import java.util.Map;

public interface BatteryService {

     List<Battery> save(List<Battery> batteryList);

     Map<String, Object> getBatteryByPostCodeRange(Long startPostalCode, Long endPostalCode);

}
