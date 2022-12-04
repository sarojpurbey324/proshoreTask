package com.proshore.battery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Battery {

    private String name;

    private long postcode;

    private long capacity;

}
