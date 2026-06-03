package com.senprac.sto.mechanic.mapper;

import com.senprac.sto.mechanic.dto.MechanicDto;
import com.senprac.sto.entity.Mechanic;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MechanicMapper {
    MechanicDto toDto(Mechanic mechanic);
    Mechanic toEntity(MechanicDto dto);
}