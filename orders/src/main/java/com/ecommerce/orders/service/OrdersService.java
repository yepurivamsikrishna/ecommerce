package com.ecommerce.orders.service;

import com.ecommerce.orders.controller.OrdersController;
import com.ecommerce.orders.dao.OrdersDao;
import com.ecommerce.orders.dto.Order;
import com.ecommerce.orders.dto.OrderProduct;
import com.ecommerce.orders.dto.Payment;
import com.ecommerce.orders.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {

    Logger logger = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    OrdersDao ordersDao;

    @Value( "${tax.percentage}" )
    float taxPercentage;
    @Value( "${shipping.charges}" )
    float shippingCharges;

    public String  createOrder(String customerId, List<OrderProduct> userProductsList){
        String newOrderId =  UUID.randomUUID().toString();
        float orderSubTotal = 0;
        for(OrderProduct userProduct : userProductsList){
           Product product = ordersDao.getProductDetails(userProduct.getProductId());
           float productTotal = product.getPrice() * userProduct.getQuantity();
           userProduct.setOrderId(newOrderId);
           userProduct.setTotal(productTotal);
           logger.info("Creating Product Order for Customer "+customerId+" for order "+newOrderId+"  Product: "+userProduct.getProductId()+ " -> "+product.getProductName()+" -> "+userProduct.getQuantity());
           ordersDao.createOrderProduct(newOrderId,userProduct.getProductId(),userProduct.getQuantity(),productTotal);
           orderSubTotal += productTotal;
       }
        float taxAmount = orderSubTotal * taxPercentage / 100;
        float orderTotal = orderSubTotal + taxAmount + shippingCharges;
        ordersDao.createOrder(newOrderId,customerId,orderSubTotal,taxAmount,shippingCharges,orderTotal,"Payment Pending");
        logger.info("New Order for customer "+customerId+" placed with orderid : "+newOrderId);
        return newOrderId;
    }

    public Order getOrderDetails(String orderId){
        List<OrderProduct> orderProducts = ordersDao.getOrderProducts(orderId);
        for(OrderProduct orderProduct : orderProducts){
            Product product = ordersDao.getProductDetails(orderProduct.getProductId());
            orderProduct.setProductName(product.getProductName());
            orderProduct.setPrice(product.getPrice());
        }
        Order order = ordersDao.getOrderDetials(orderId);
        order.setProductsList(orderProducts);
        if(order.getOrderBillingAddressId() != null){
            order.setBillingAddress(ordersDao.getAddress(order.getOrderBillingAddressId()));
        }
        if(order.getOrderShippingAddressId() != null){
            order.setShippingAddress(ordersDao.getAddress(order.getOrderShippingAddressId()));
        }
        if(order.getOrderPaymentId() != null){
            order.setPayment(ordersDao.getPayment(order.getOrderPaymentId()));
        }
        logger.info("Returning Order Details for Order Id "+orderId);
        return order;
    }

    public void payOrder(String orderId, Payment payment){
        String paymentId = UUID.randomUUID().toString();
        ordersDao.payOrder(orderId,paymentId,new Date(new java.util.Date().getTime()),payment.getPaymentConfirmationNo(),payment.getGetPaymentMethod());
        ordersDao.updatePayment(orderId,paymentId);
        logger.info("Payment Details Updated Successfully for OrderId "+orderId);
    }

    public int deliverOrder(String orderId,String billingAddressId,String shippingAddressId){
        int res =  ordersDao.deliverOrder(orderId,billingAddressId,shippingAddressId);
        logger.info("Delivery details updated Successfully for OrderId "+orderId);
        return res;
    }

    public int cancelOrder(String orderId){
       int res =  ordersDao.cancelOrder(orderId);
       logger.info("Order cancelled Successfully for OrderId "+orderId);
       return res;
    }








}
