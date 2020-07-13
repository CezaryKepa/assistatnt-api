package com.businessassistant.assistantapi.order_details;

import com.businessassistant.assistantapi.gen.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }
    public Optional<OrderDetails> findById(Long id) {
        return orderDetailsRepository.findById(id).map(OrderDetailsMapper::orderDetailsToDto);
    }
}
