package com.businessassistant.assistantapi.client;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.*;
import com.businessassistant.assistantapi.gen.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class ClientEndpoint {

    private ClientService clientService;

    @Autowired
    public ClientEndpoint(ClientService clientService) {
        this.clientService = clientService;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/client", localPart = "getClientRequest")
    @ResponsePayload
    public GetClientResponse getClientById(@RequestPayload GetClientRequest getClientRequest) {
        Optional<Client> client = clientService.findById(getClientRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        GetClientResponse getClientResponse = new GetClientResponse();

        if (!client.isPresent()) {
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No client with this id");
            throw new ServiceFaultException("Not Found", serviceStatus);
        }

        Client payload = client.get();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        getClientResponse.setOrder(payload);
        getClientResponse.setServiceStatus(serviceStatus);

        return getClientResponse;
    }
}
