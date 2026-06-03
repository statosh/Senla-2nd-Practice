package com.senprac.sto.repairorder.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairOrderDto {
    private Long id;
    private LocalDate receptionDate;
    private String faultDescription;
    private String status;
    private Long carId;
    private Long mechanicId;
}