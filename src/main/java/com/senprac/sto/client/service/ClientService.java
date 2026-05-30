package com.senprac.sto.client.service;

import com.senprac.sto.client.repository.ClientRepository;
import com.senprac.sto.entity.Client;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}