package com.ecommerce.orders.dao;

import com.ecommerce.orders.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrdersDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Product getProductDetails(String productId){
       return jdbcTemplate.queryForObject("select * from products where product_id=?",new Object[]{productId},new ProductRowMapper());
    }

    public int createOrder(String orderId,String customerId,float orderSubTotal,float orderTax,float orderShippingCharges,float orderTotal,String orderStatus){
        String query = "insert into orders(order_id,order_customer_id,order_subtotal,order_tax,order_shipping_charges,order_total,order_status) values(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(query,new Object[]{orderId,customerId,orderSubTotal,orderTax,orderShippingCharges,orderTotal,orderStatus});
    }

    public int createOrderProduct(String orderId,String productId,int quantity,float total){
       String query = "insert into orders_products values(?,?,?,?)";
       return jdbcTemplate.update(query,new Object[]{orderId,productId,quantity,total});
    }

    public List<OrderProduct> getOrderProducts(String orderId){
        String query = "select * from orders_products where order_id=?";
        return jdbcTemplate.query(query,new Object[]{orderId},new OrderProductRowMapper());
    }

    public  Order getOrderDetials(String orderId){
        String query = "select * from orders where order_id=?";
        return jdbcTemplate.queryForObject(query,new Object[]{orderId},new OrderRowMapper());
    }

    public int cancelOrder(String orderId){
        String query = "update orders set order_status=? where order_id=?";
        return  jdbcTemplate.update(query,new Object[]{"Cancelled",orderId});
    }

    public int deliverOrder(String orderId,String billingAddressId,String shippingAddressId){
        String query = "update orders set order_billing_address_id=?,order_shipping_address_id=?,order_status=? where order_id=?";
        return  jdbcTemplate.update(query,new Object[]{billingAddressId,shippingAddressId,"Ready to Ship",orderId});
    }

    public Address getAddress(String addressId){
        String query = "select * from address where address_id=?";
        return jdbcTemplate.queryForObject(query,new Object[]{addressId},new AddressRowMapper());
    }

    public int payOrder(String orderId, String paymentId, Date paymentDate,String paymentNo,String paymentMethod){
        String query = "insert into payments values(?,?,?,?,?)";
        return jdbcTemplate.update(query,new Object[]{orderId,paymentId,paymentDate,paymentNo,paymentMethod});
    }

    public int updatePayment(String orderId, String paymentId){
        String query = "update orders set order_payment_id=?,order_status=? where order_id=?";
        return  jdbcTemplate.update(query,new Object[]{paymentId,"Confirmed. Paid",orderId});
    }

    public Payment getPayment(String paymentId){
        String query = "select * from payments where order_payment_id=?";
        return  jdbcTemplate.queryForObject(query,new Object[]{paymentId},new PaymentRowMapper());
    }

}

class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getString("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setPrice(rs.getFloat("price"));
        return product;
    }
}

class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int i) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getString("address_id"));
        address.setAddress(rs.getString("address"));
        return address;
    }
}

class PaymentRowMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs, int i) throws SQLException {
        Payment payment = new Payment();
        payment.setOrderId(rs.getString("order_id"));
        payment.setPaymentId(rs.getString("order_payment_id"));
        payment.setPaymentDate(rs.getDate("payment_date"));
        payment.setPaymentConfirmationNo(rs.getString("payment_confirmation_no"));
        payment.setGetPaymentMethod(rs.getString("payment_method"));
        return payment;
    }
}

class OrderProductRowMapper implements RowMapper<OrderProduct> {

    @Override
    public OrderProduct mapRow(ResultSet rs, int i) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(rs.getString("order_id"));
        orderProduct.setProductId(rs.getString("product_id"));
        orderProduct.setQuantity(rs.getInt("quantity"));
        orderProduct.setTotal(rs.getFloat("total"));
        return orderProduct;
    }
}

class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getString("order_id"));
        order.setCustomerId(rs.getString("order_customer_id"));
        order.setSubTotal(rs.getFloat("order_subtotal"));
        order.setOrderTax(rs.getFloat("order_tax"));
        order.setOrderShippingCharges(rs.getFloat("order_shipping_charges"));
        order.setOrderTotal(rs.getFloat("order_total"));
        order.setOrderStatus(rs.getString("order_status"));
        order.setOrderBillingAddressId(rs.getString("order_billing_address_id"));
        order.setOrderShippingAddressId(rs.getString("order_shipping_address_id"));
        order.setOrderPaymentId(rs.getString("order_payment_id"));

        return order;
    }
}
