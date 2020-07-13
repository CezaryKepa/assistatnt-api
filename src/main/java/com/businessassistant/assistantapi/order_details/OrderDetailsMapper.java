package com.businessassistant.assistantapi.order_details;

public class OrderDetailsMapper {


    public static OrderDetails dtoToOrderDetails(com.businessassistant.assistantapi.gen.OrderDetails orderDetails) {
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

    public static com.businessassistant.assistantapi.gen.OrderDetails orderDetailsToDto(OrderDetails orderDetails){
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
