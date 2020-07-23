package com.businessassistant.assistantapi.order;


import com.businessassistant.assistantapi.asset.AssetMapper;
import com.businessassistant.assistantapi.client.ClientMapper;
import com.businessassistant.assistantapi.order_details.OrderDetails;
import com.businessassistant.assistantapi.order_details.OrderDetailsMapper;

import java.util.stream.Collectors;


public class OrderMapper {

     public static com.businessassistant.assistantapi.gen.Order toDto(Order order) {
        com.businessassistant.assistantapi.gen.Order dto = new com.businessassistant.assistantapi.gen.Order();
        dto.setId(order.getId());
        dto.setClient(ClientMapper.toDto(order.getClient()));
        dto.setOrderStatus(order.getStatus().name());
        dto.setAssets(order.getAssets()
                .stream()
                .map(AssetMapper::toDto)
                .collect(Collectors.toList())
        );
        dto.setOrderDetails(OrderDetailsMapper.orderDetailsToDto(order.getOrderDetails()));
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
        entity.setOrderDetails(OrderDetailsMapper.dtoToOrderDetails(dto.getOrderDetails()));
        return entity;
    }

}
