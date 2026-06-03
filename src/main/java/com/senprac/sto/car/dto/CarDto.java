package com.senprac.sto.car.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private int productionYear;
    private String vin;
    private Long clientId;
}