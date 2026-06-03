package com.senprac.sto.car.mapper;

import com.senprac.sto.car.dto.CarDto;
import com.senprac.sto.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    @Mapping(source = "client.id", target = "clientId")
    CarDto toDto(Car car);

    @Mapping(source = "clientId", target = "client.id")
    Car toEntity(CarDto dto);
}