package com.proshore.module.battery;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.proshore.battery.controller.BatteryController;
import com.proshore.battery.model.Battery;
import com.proshore.battery.service.BatteryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BatteryController.class)
class BatteryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatteryService batteryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void saveBattery() throws Exception {
        List<Battery> expectedBatteries = BatteryHelperTest.prepareBatteries();
        this.mockMvc.perform(MockMvcRequestBuilders.post("/battery")
                        .content(this.objectMapper.writeValueAsString(expectedBatteries))
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }


    @Test
    void getNamesBetweenPostRange() throws Exception {
        Map<String, Object> expectedBatteryNames = BatteryHelperTest.prepareBatteriesInfo();
        when(this.batteryService.getBatteryByPostCodeRange(10000L, 25000L)).thenReturn(expectedBatteryNames);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/battery")
                        .param("startPostalCode", "10000").param("endPostalCode", "25000")
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(this.objectMapper.writeValueAsString(expectedBatteryNames)));
    }
}
