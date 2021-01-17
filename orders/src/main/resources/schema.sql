drop table if exists customers;
drop table if exists address;
drop table if exists customer_address;
drop table if exists products;
drop table if exists payments;
drop table if exists orders;
drop table if exists orders_products;


create table if not exists customers(customer_id varchar(30),customer_name varchar(50));
create table if not exists address(address_id varchar(30),address varchar(100));
create table if not exists customer_address(customer_id varchar(30),address_id varchar(30));
create table if not exists products(product_id varchar(30),product_name varchar(30),price float);
create table if not exists payments(order_id varchar(50),order_payment_id varchar(50),payment_date timestamp,payment_confirmation_no varchar(30),payment_method varchar(30));
create table if not exists orders(order_id varchar(50),order_customer_id
 varchar(30),order_subtotal float,order_tax float,order_shipping_charges float,order_total float,order_status varchar(30),order_billing_address_id varchar(30),order_shipping_address_id varchar(30),order_payment_id varchar(50));
create table orders_products(order_id varchar(50),product_id varchar(30),quantity int,total float);




insert into customers values('C001','Tony');
insert into customers values('C002','Hulk');
insert into customers values('C003','Captain');

insert into address values('A001','New York');
insert into address values('A002','California');
insert into address values('A003','Texas');

insert into customer_address values('C001','A001');
insert into customer_address values('C002','A002');
insert into customer_address values('C003','A003');

insert into products values('P001','Armor',1599.99);
insert into products values('P002','Goggles',799);
insert into products values('P003','Shirt',900);
insert into products values('P004','Trouser',1200);
insert into products values('P005','Shield',1299.99);
insert into products values('P006','Cap',349);


