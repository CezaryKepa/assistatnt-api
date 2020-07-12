package com.businessassistant.assistantapi.client;

public class ClientMapper {
    public static com.businessassistant.assistantapi.gen.Client toDto(Client client){
        com.businessassistant.assistantapi.gen.Client dto= new com.businessassistant.assistantapi.gen.Client();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setFirstname(client.getFirstname());
        dto.setSurname(client.getSurname());
        return dto;
    }

    public static Client toEntity(com.businessassistant.assistantapi.gen.Client client) {
        Client entity = new Client();
        entity.setId(client.getId());
        entity.setEmail(client.getEmail());
        entity.setFirstname(client.getFirstname());
        entity.setSurname(client.getSurname());
        return entity;
    }
}
