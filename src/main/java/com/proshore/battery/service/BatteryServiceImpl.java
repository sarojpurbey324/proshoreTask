package com.proshore.battery.service;

import com.proshore.battery.dao.BatteryDao;
import com.proshore.battery.model.Battery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryDao batteryDao;

    public BatteryServiceImpl(BatteryDao batteryDao) {
        this.batteryDao = batteryDao;
    }

    @Override
    public List<Battery> save(List<Battery> batteryList) {
        return batteryDao.save(batteryList);
    }

    @Override
    public Map<String, Object> getBatteryByPostCodeRange(Long startPostalCode, Long endPostalCode) {
        return batteryDao.getBatteryByPostCodeRange(startPostalCode, endPostalCode);
    }

}
