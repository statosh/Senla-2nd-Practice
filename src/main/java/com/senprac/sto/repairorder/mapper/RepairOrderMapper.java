package com.senprac.sto.repairorder.mapper;

import com.senprac.sto.repairorder.dto.RepairOrderDto;
import com.senprac.sto.entity.RepairOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RepairOrderMapper {
    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "mechanic.id", target = "mechanicId")
    RepairOrderDto toDto(RepairOrder order);

    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "mechanicId", target = "mechanic.id")
    RepairOrder toEntity(RepairOrderDto dto);
}