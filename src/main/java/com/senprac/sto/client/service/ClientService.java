package com.senprac.sto.client.service;

import com.senprac.sto.client.dto.ClientDto;
import com.senprac.sto.client.mapper.ClientMapper;
import com.senprac.sto.client.repository.ClientRepository;
import com.senprac.sto.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    public Optional<ClientDto> getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone).map(clientMapper::toDto);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}