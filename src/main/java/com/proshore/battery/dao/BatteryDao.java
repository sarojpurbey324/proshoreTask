package com.proshore.battery.dao;

import com.proshore.battery.model.Battery;
import com.proshore.utils.QueryAndParamsDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

public interface BatteryDao {

    List<Battery> save(List<Battery> batteryList);

    QueryAndParamsDTO buildSaveQuery(List<MapSqlParameterSource> paramsToSaveBattery);

    Map<String, Object> getBatteryByPostCodeRange(Long startingCode, Long endingCode);

    QueryAndParamsDTO buildAvgAndTotalCapacityQuery(Map<String, Object> params);

    QueryAndParamsDTO buildGetNamesQuery(Map<String, Object> params);
}
