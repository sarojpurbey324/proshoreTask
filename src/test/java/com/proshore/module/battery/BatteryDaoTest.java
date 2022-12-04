package com.proshore.module.battery;

import com.proshore.battery.dao.BatteryDao;
import com.proshore.battery.model.Battery;
import com.proshore.utils.QueryAndParamsDTO;
import com.proshore.utils.SqlUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.*;
import java.util.stream.Stream;

@SpringBootTest()
class BatteryDaoTest {

    @Autowired
    private BatteryDao batteryDao;

    @Test
    void buildAvgAndTotalCapacityQueryAndbuildGetNamesQueryTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("startCode", 10000L);
        params.put("endCode", 25000L);
        QueryAndParamsDTO avgAndTotalCapacityQueryAndParamsDTO = batteryDao.buildAvgAndTotalCapacityQuery(params);
        String expectedQuery = "select sum(capacity) as total_capacity, avg(capacity) as average_capacity from battery where post_code between  :startCode and :endCode";
        Map<String, Object> expectedParams = new HashMap<String, Object>() {{
            put("startCode", 10000L);
            put("endCode", 25000L);
        }};
        SqlUtil.assertSqlStrings(expectedQuery, avgAndTotalCapacityQueryAndParamsDTO.getQuery());
        SqlUtil.assertEqualForParams(expectedParams, avgAndTotalCapacityQueryAndParamsDTO.getParams());

        Battery battery = new Battery();
        battery.setName("Cannington");
        battery.setPostcode(6107);
        battery.setCapacity(13500);
        QueryAndParamsDTO namesQueryAndParamsDTO = batteryDao.buildGetNamesQuery(params);
        String expectedQueryForNames = "select name from battery where post_code between  :startCode and :endCode order by name asc";
        SqlUtil.assertSqlStrings(expectedQueryForNames, namesQueryAndParamsDTO.getQuery());
        SqlUtil.assertEqualForParams(expectedParams, namesQueryAndParamsDTO.getParams());
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new Battery("Cannington", 6107, 13500)),
                Arguments.of(new Battery("Hay Street", 6000, 23500))
        );
    }

    @ParameterizedTest
    @MethodSource("generator")
    void buildSaveQueryTest(Battery battery) {
        // just for the sake of demonstrating the use of Streams, used ParameterizedTest, we could instead use BatteryHelperTest.prepareBatteries here
        List<Battery> batteries = Collections.singletonList(battery);
        QueryAndParamsDTO saveQueryAndParamsDTO = batteryDao.buildSaveQuery(getExpectedParamsForBattery(batteries));
        String expectedQuery = "insert into BATTERY (name, capacity, post_code) values (:name, :post_code, :capacity)";
        SqlUtil.assertSqlStrings(expectedQuery, saveQueryAndParamsDTO.getQuery());
        SqlUtil.assertEqualForMapSqlParams(getExpectedParamsForBattery(batteries), saveQueryAndParamsDTO.getMapSqlParams());
    }

    private List<MapSqlParameterSource> getExpectedParamsForBattery(List<Battery> batteryList) {
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
}
