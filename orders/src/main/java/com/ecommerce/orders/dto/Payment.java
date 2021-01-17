package com.ecommerce.orders.dto;

import java.sql.Date;

public class Payment {
    String orderId,paymentId;
    Date paymentDate;
    String paymentConfirmationNo,getPaymentMethod;

    public Payment() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentConfirmationNo() {
        return paymentConfirmationNo;
    }

    public void setPaymentConfirmationNo(String paymentConfirmationNo) {
        this.paymentConfirmationNo = paymentConfirmationNo;
    }

    public String getGetPaymentMethod() {
        return getPaymentMethod;
    }

    public void setGetPaymentMethod(String getPaymentMethod) {
        this.getPaymentMethod = getPaymentMethod;
    }
}
