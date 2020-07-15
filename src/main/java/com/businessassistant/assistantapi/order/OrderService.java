package com.businessassistant.assistantapi.order;


import com.businessassistant.assistantapi.asset.Asset;
import com.businessassistant.assistantapi.asset.AssetService;
import com.businessassistant.assistantapi.client.Client;
import com.businessassistant.assistantapi.client.ClientService;
import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.Order;
import com.businessassistant.assistantapi.gen.ServiceStatus;

import com.businessassistant.assistantapi.order_details.OrderDetails;
import com.businessassistant.assistantapi.order_details.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private AssetService assetService;
    private ClientService clientService;
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, AssetService assetService, ClientService clientService, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.assetService = assetService;
        this.clientService = clientService;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    List<Order> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id).map(OrderMapper::toDto);
    }


    public Order save(Order order){
        com.businessassistant.assistantapi.order.Order entity = OrderMapper.toEntity(order);

        List<Asset> assets = assetService.saveAll(entity.getAssets());
        Client save = clientService.save(entity.getClient());
        OrderDetails orderDetails = orderDetailsRepository.save(entity.getOrderDetails());

        entity.setAssets(assets);
        entity.setClient(save);
        entity.setOrderDetails(orderDetails);

        orderRepository.save(entity);
        return order;
    }

    public Order changeStatus(long id) {
        Optional<com.businessassistant.assistantapi.order.Order> orderById = orderRepository.findById(id);
        ServiceStatus serviceStatus = new ServiceStatus();
        if(!orderById.isPresent()){
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No order with this id");
            throw new ServiceFaultException("Not Found",serviceStatus);
        }
        com.businessassistant.assistantapi.order.Order order = orderById.get();
        order.setStatus(OrderStatus.nextStatus(order.getStatus()));
        orderRepository.save(order);
        return OrderMapper.toDto(order);
    }
}
