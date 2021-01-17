package com.ecommerce.orders.controller;

import com.ecommerce.orders.dto.Order;
import com.ecommerce.orders.dto.OrderProduct;
import com.ecommerce.orders.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrdersController {

    Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    OrdersService ordersService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createOrder(@RequestBody  Order order){
        logger.info("Create Order Request received from Customer Id "+order.getCustomerId());
        String orderId = ordersService.createOrder(order.getCustomerId(), order.getProductsList());
        return ResponseEntity.ok(orderId);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Order> getOrderDetials(@RequestParam  String orderId){
        logger.info("Get Order Details Request received for Order Id "+orderId);
        Order order = ordersService.getOrderDetails(orderId);
        return  ResponseEntity.ok(order);
    }



    @PutMapping(value = "/payment")
    public ResponseEntity payOrder(@RequestBody Order order){
        logger.info("Payment Request received for Order Id "+order.getOrderId());
        ordersService.payOrder(order.getOrderId(),order.getPayment());
        return  null;
    }

    @PutMapping(value = "/deliver")
    public ResponseEntity deliverOrder(@RequestBody Order order){
        logger.info("Deliver Order Request received for Order Id "+order.getOrderId());
        ordersService.deliverOrder(order.getOrderId(),order.getOrderBillingAddressId(),order.getOrderShippingAddressId());
        return  null;
    }

    @PutMapping(value = "/cancel")
    public ResponseEntity cancelOrder(@RequestBody Order order){
        logger.info("Cancel Order Request received for Order Id "+order.getOrderId());
        ordersService.cancelOrder(order.getOrderId());
        return  null;
    }
}
