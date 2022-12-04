package com.proshore.battery.dao;

import com.proshore.battery.model.Battery;
import com.proshore.utils.QueryAndParamsDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BatteryDaoImpl implements BatteryDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BatteryDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Battery> save(List<Battery> batteryList) {
        if (!batteryList.isEmpty()) {
            QueryAndParamsDTO saveQueryAndParamsDTO = buildSaveQuery(getParamsForBattery(batteryList));
            int[] i = namedParameterJdbcTemplate.batchUpdate(saveQueryAndParamsDTO.getQuery(), saveQueryAndParamsDTO.getMapSqlParams().toArray(new MapSqlParameterSource[0]));
            if (i.length == batteryList.size()) {
                return batteryList;
            }
        }
        return Collections.emptyList();
    }

    public QueryAndParamsDTO buildSaveQuery(List<MapSqlParameterSource> paramsToSaveBattery) {
        String sql = "insert into BATTERY (name, capacity, post_code) values (:name, :post_code, :capacity)";
        QueryAndParamsDTO queryAndParamsDTO = new QueryAndParamsDTO();
        queryAndParamsDTO.setQuery(sql);
        queryAndParamsDTO.setMapSqlParams(paramsToSaveBattery);
        return queryAndParamsDTO;
    }

    private List<MapSqlParameterSource> getParamsForBattery(List<Battery> batteryList) {
        List<MapSqlParameterSource> paramsToSaveBattery = new ArrayList<>();
        for (Battery battery : batteryList) {
            MapSqlParameterSource source = new MapSqlParameterSource();
            source.addValue("name", battery.getName());
            source.addValue("post_code", battery.getPostcode());
            source.addValue("capacity", battery.getCapacity());
            paramsToSaveBattery.add(source);
        }
        return paramsToSaveBattery;
    }

    @Override
    public Map<String, Object> getBatteryByPostCodeRange(Long startingCode, Long endingCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("startCode", startingCode);
        params.put("endCode", endingCode);
        QueryAndParamsDTO avgAndTotalCapacityQueryAndParamsDTO = buildAvgAndTotalCapacityQuery(params);
        Map<String, Object> responseMap = namedParameterJdbcTemplate.queryForMap(avgAndTotalCapacityQueryAndParamsDTO.getQuery(), avgAndTotalCapacityQueryAndParamsDTO.getParams());
        QueryAndParamsDTO namesQueryAndParamsDTO = buildGetNamesQuery(params);
        List<String> namesList = namedParameterJdbcTemplate.queryForList(namesQueryAndParamsDTO.getQuery(), namesQueryAndParamsDTO.getParams(), String.class);
        responseMap.put("names", namesList);
        return responseMap;
    }

    public QueryAndParamsDTO buildGetNamesQuery(Map<String, Object> params) {
        String sql = "select name from battery where post_code between  :startCode and :endCode order by name asc";
        return new QueryAndParamsDTO(sql, params);
    }

    public QueryAndParamsDTO buildAvgAndTotalCapacityQuery(Map<String, Object> params) {
        String sql = "select sum(capacity) as total_capacity, avg(capacity) as average_capacity from battery where post_code between  :startCode and :endCode";
        return new QueryAndParamsDTO(sql, params);
    }
}
