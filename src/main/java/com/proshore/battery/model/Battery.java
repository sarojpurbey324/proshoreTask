package com.proshore.battery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Battery {

    @NotBlank(message = "Name is mandatory")
    private String name;


    private long postcode;

    private long capacity;

}
