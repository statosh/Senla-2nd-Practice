package com.senprac.sto.client.mapper;

import com.senprac.sto.client.dto.ClientDto;
import com.senprac.sto.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto dto);
}