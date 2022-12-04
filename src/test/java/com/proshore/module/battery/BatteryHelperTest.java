package com.proshore.module.battery;

import com.proshore.battery.model.Battery;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BatteryHelperTest {

    public static Map<String, Object> prepareBatteriesInfo() {
        Map<String, Object> responseMap = new HashMap<>();
        List<String> namesList = new LinkedList<>();
        namesList.add("Cannington");
        namesList.add("Hay Street");
        responseMap.put("names", namesList);
        responseMap.put("average_capacity", 14000L);
        responseMap.put("total_capacity", 28000L);
        return responseMap;
    }

    public static List<Battery> prepareBatteries() {
        List<Battery> batteryList = new LinkedList<>();
        batteryList.add(new Battery("Cannington", 6107, 13500));
        batteryList.add(new Battery("Hay Street", 6000, 23500));
        return batteryList;
    }
}
