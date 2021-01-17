package com.ecommerce.orders.dto;

import java.util.List;

public class Order {
    String orderId,customerId;
    float subTotal,orderTax,orderShippingCharges,orderTotal;
    String orderStatus;
    String orderPaymentId;
    String orderBillingAddressId,orderShippingAddressId;
    List<OrderProduct> productsList;

    Address billingAddress,shippingAddress;
    Payment payment;

    public Order() {
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderProduct> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<OrderProduct> productsList) {
        this.productsList = productsList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(float orderTax) {
        this.orderTax = orderTax;
    }

    public float getOrderShippingCharges() {
        return orderShippingCharges;
    }

    public void setOrderShippingCharges(float orderShippingCharges) {
        this.orderShippingCharges = orderShippingCharges;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPaymentId() {
        return orderPaymentId;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.orderPaymentId = orderPaymentId;
    }

    public String getOrderBillingAddressId() {
        return orderBillingAddressId;
    }

    public void setOrderBillingAddressId(String orderBillingAddressId) {
        this.orderBillingAddressId = orderBillingAddressId;
    }

    public String getOrderShippingAddressId() {
        return orderShippingAddressId;
    }

    public void setOrderShippingAddressId(String orderShippingAddressId) {
        this.orderShippingAddressId = orderShippingAddressId;
    }
}
