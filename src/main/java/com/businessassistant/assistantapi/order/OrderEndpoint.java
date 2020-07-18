package com.businessassistant.assistantapi.order;

import com.businessassistant.assistantapi.exception.ServiceFaultException;
import com.businessassistant.assistantapi.gen.*;
import com.businessassistant.assistantapi.gen.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class OrderEndpoint {
    private OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/order", localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrderById(@RequestPayload GetOrderRequest getOrderRequest){
        Optional<Order> order = orderService.findById(getOrderRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        GetOrderResponse getOrderResponse = new GetOrderResponse();

        if(!order.isPresent()){
            serviceStatus.setStatusCode("404");
            serviceStatus.setMessage("No order with this id");
            throw new ServiceFaultException("Not Found",serviceStatus);
        }

        Order payload =order.get();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        getOrderResponse.setOrder(payload);
        getOrderResponse.setServiceStatus(serviceStatus);

        return getOrderResponse;
    }
    @PayloadRoot(namespace = "http://www.kepa.com/api/order", localPart = "getAllOrdersRequest")
    @ResponsePayload
    public GetAllOrdersResponse findAll(@RequestPayload GetAllOrdersRequest getAllOrdersRequest){

        List<Order> orders = orderService.findAll();
        GetAllOrdersResponse getAllOrdersResponse = new GetAllOrdersResponse();

        getAllOrdersResponse.setOrders(orders);

        return getAllOrdersResponse;
    }
    @PayloadRoot(namespace = "http://www.kepa.com/api/order", localPart = "postOrderRequest")
    @ResponsePayload
    public PostOrderResponse save(@RequestPayload PostOrderRequest postOrderRequest){
        ServiceStatus serviceStatus = new ServiceStatus();
        Order saved = orderService.save(postOrderRequest.getOrder());

        PostOrderResponse postOrderResponse = new PostOrderResponse();
        postOrderResponse.setOrder(saved);

        serviceStatus.setStatusCode("201");
        serviceStatus.setMessage("Order was created");

        postOrderResponse.setServiceStatus(serviceStatus);
        return postOrderResponse;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/order", localPart = "deleteOrderRequest")
    @ResponsePayload
    public DeleteOrderResponse delete(@RequestPayload DeleteOrderRequest deleteOrderRequest){
        ServiceStatus serviceStatus = new ServiceStatus();
        Order saved = orderService.delete(deleteOrderRequest.getId());

        DeleteOrderResponse deleteOrderResponse = new DeleteOrderResponse();
        deleteOrderResponse.setOrder(saved);

        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("Order was deleted");

        deleteOrderResponse.setServiceStatus(serviceStatus);
        return deleteOrderResponse;
    }

    @PayloadRoot(namespace = "http://www.kepa.com/api/order", localPart = "changeStatusRequest")
    @ResponsePayload
    public ChangeStatusResponse changeStatus(@RequestPayload ChangeStatusRequest changeStatusRequest){
        Order order = orderService.changeStatus(changeStatusRequest.getId());
        ServiceStatus serviceStatus = new ServiceStatus();
        ChangeStatusResponse changeStatusResponse = new ChangeStatusResponse();

        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("OK");
        changeStatusResponse.setOrder(order);
        changeStatusResponse.setServiceStatus(serviceStatus);

        return changeStatusResponse;
    }

}
