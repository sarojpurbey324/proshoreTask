package com.proshore.module.battery;

import com.proshore.battery.dao.BatteryDao;
import com.proshore.battery.model.Battery;
import com.proshore.battery.service.BatteryService;
import com.proshore.battery.service.BatteryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BatteryServiceTest {

    @MockBean
    private BatteryDao batteryDao;

    private BatteryService batteryService;

    @BeforeEach
    void setup() {
        batteryService = new BatteryServiceImpl(batteryDao);
    }

    @Test
    void saveBatteryTest() {
        List<Battery> expectedBatteries = BatteryHelperTest.prepareBatteries();
        when(this.batteryDao.save(expectedBatteries)).thenReturn(expectedBatteries);
        List<Battery> batteries = this.batteryService.save(expectedBatteries);
        assertEquals(expectedBatteries, batteries);
    }

    @Test
    void fetchBatteryListByRangeTest() {
        Map<String, Object> expectedBatteriesInfo = BatteryHelperTest.prepareBatteriesInfo();
        when(this.batteryDao.getBatteryByPostCodeRange(10000L, 25000L)).thenReturn(expectedBatteriesInfo);
        Map<String, Object> actualBatteriesInfo = this.batteryService.getBatteryByPostCodeRange(10000L, 25000L);
        assertEquals(expectedBatteriesInfo, actualBatteriesInfo);
    }


}
