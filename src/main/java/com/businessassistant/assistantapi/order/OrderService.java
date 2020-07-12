package com.businessassistant.assistantapi.order;


import com.businessassistant.assistantapi.asset.Asset;
import com.businessassistant.assistantapi.asset.AssetRepository;
import com.businessassistant.assistantapi.client.Client;
import com.businessassistant.assistantapi.client.ClientRepository;
import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.Order;
import com.businessassistant.assistantapi.gen.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private AssetRepository assetRepository;
    private ClientRepository clientRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, AssetRepository assetRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.assetRepository = assetRepository;
        this.clientRepository = clientRepository;
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

    /*
        TODO: Instead of using repositories inject services that also check for duplicates
     */

    public Order save(Order order){
        com.businessassistant.assistantapi.order.Order entity = OrderMapper.toEntity(order);
        //TODO:add serial numbers to asset
        List<Asset> assets = assetRepository.saveAll(entity.getAssets());
        Client save = clientRepository.save(entity.getClient());
        entity.setAssets(assets);
        entity.setClient(save);

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
