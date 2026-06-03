package com.senprac.sto.mechanic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MechanicDto {
    private Long id;
    private String fullName;
    private String specialization;
    private String phone;
}