package com.businessassistant.assistantapi.client;


import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<com.businessassistant.assistantapi.gen.Client> findById(Long id) {
        return clientRepository.findById(id).map(ClientMapper::toDto);
    }

    public Client save(Client client) {
        Optional<Client> clientByEmail = clientRepository.findByEmail(client.getEmail());
        ServiceStatus serviceStatus = new ServiceStatus();
        if(clientByEmail.isPresent()){
            serviceStatus.setStatusCode("409");
            serviceStatus.setMessage("There already is client with this e-mail");
            throw new ServiceFaultException("Conflict",serviceStatus);
        }
        return save(client);
    }
}
