package com.businessassistant.assistantapi.order;


import com.businessassistant.assistantapi.asset.AssetMapper;
import com.businessassistant.assistantapi.client.ClientMapper;

import java.util.stream.Collectors;


public class OrderMapper {

     static com.businessassistant.assistantapi.gen.Order toDto(Order order) {
        com.businessassistant.assistantapi.gen.Order dto = new com.businessassistant.assistantapi.gen.Order();
        dto.setId(order.getId());
        dto.setClient(ClientMapper.toDto(order.getClient()));
        dto.setOrderStatus(order.getStatus().name());
        dto.setAssets(order.getAssets()
                .stream()
                .map(AssetMapper::toDto)
                .collect(Collectors.toList())
        );
        dto.setOrderDetails(OrderMapper.orderDetailsToDto(order.getOrderDetails()));
        return dto;
    }

    static Order toEntity(com.businessassistant.assistantapi.gen.Order dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setClient(ClientMapper.toEntity(dto.getClient()));
        entity.setStatus(OrderStatus.valueOf(dto.getOrderStatus()));
        entity.setAssets(dto.getAssets()
                .stream()
                .map(AssetMapper::toEntity)
                .collect(Collectors.toList())
        );
        entity.setOrderDetails(OrderMapper.dtoToOrderDetails(dto.getOrderDetails()));
        return entity;
    }

    static OrderDetails dtoToOrderDetails(com.businessassistant.assistantapi.gen.OrderDetails orderDetails) {
         OrderDetails entity =new OrderDetails();
        entity.setId(orderDetails.getId());
        entity.setCity(orderDetails.getCity());
        entity.setCountry(orderDetails.getCountry());
        entity.setHouseNumber(orderDetails.getHouseNumber());
        entity.setStreet(orderDetails.getStreet());
        entity.setTelephone(orderDetails.getTelephone());
        entity.setZip(orderDetails.getZip());
        return entity;
     }

    static com.businessassistant.assistantapi.gen.OrderDetails orderDetailsToDto(OrderDetails orderDetails){
        com.businessassistant.assistantapi.gen.OrderDetails dto = new com.businessassistant.assistantapi.gen.OrderDetails();
        dto.setId(orderDetails.getId());
        dto.setCity(orderDetails.getCity());
        dto.setCountry(orderDetails.getCountry());
        dto.setHouseNumber(orderDetails.getHouseNumber());
        dto.setStreet(orderDetails.getStreet());
        dto.setTelephone(orderDetails.getTelephone());
        dto.setZip(orderDetails.getZip());
        return dto;
    }
}
