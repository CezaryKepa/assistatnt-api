package com.businessassistant.assistantapi.order_details;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.*;
import com.businessassistant.assistantapi.gen.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class OrderDetailsEndpoint {
    private OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsEndpoint(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/order-details", localPart = "getOrderDetailsRequest")
    @ResponsePayload
    public GetOrderDetailsResponse getOrderDetailsById(@RequestPayload GetOrderDetailsRequest getOrderDetailsRequest){
        Optional<OrderDetails> orderDetails  = orderDetailsService.findById(getOrderDetailsRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        GetOrderDetailsResponse getOrderDetailsResponse = new GetOrderDetailsResponse();

        if(!orderDetails.isPresent()){
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No order-details with this id");
            throw new ServiceFaultException("Not Found",serviceStatus);
        }

        OrderDetails payload =orderDetails.get();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        getOrderDetailsResponse.setOrder(payload);
        getOrderDetailsResponse.setServiceStatus(serviceStatus);

        return getOrderDetailsResponse;
    }
}
