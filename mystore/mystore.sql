create database MYSTORE;

CREATE TABLE `MYSTORE`.`ACCOUNT` 
   (	
    `ACCOUNTID` DOUBLE, 
	`NAME` VARCHAR(50), 
	`PHONE` VARCHAR(50), 
	`ADDRESS` VARCHAR(50), 
	`PASSWORD` VARCHAR(255), 
	`EMAIL` VARCHAR(50), 	
	`ROLE` VARCHAR(50)
   );
   
Insert into MYSTORE.ACCOUNT (ACCOUNTID,NAME,PHONE,ADDRESS,PASSWORD,EMAIL,ROLE) values 
(0001,'John','012345689','001 first street, Ny','password','user@gmail.com','Admin');

CREATE TABLE `MYSTORE`.`PRODUCT` 
(	
	`sku` INT(20) AUTO_INCREMENT, 
	`name` VARCHAR(20), 
	`quantity` INT(5), 
	`unit_price` DECIMAL(5,2), 	
	 PRIMARY KEY(sku)
)ENGINE=INNODB;
   
CREATE TABLE `MYSTORE`.`ORDER` 
(	
	`id` INT(10) AUTO_INCREMENT, 
	`amount` DECIMAL(5,2), 
	`created_date` DATE,
	 PRIMARY KEY(id)	
)ENGINE=INNODB;
   
CREATE TABLE `MYSTORE`.`ORDER_ITEM` 
(	
	`order_item_id` INT(10) AUTO_INCREMENT, 
	`sold_quantity` INT(5), 
	`unit_price` DECIMAL(5,2), 
	`product_sku` INT(20),
	`order_id` INT(10),
	 PRIMARY KEY(order_item_id),
	 FOREIGN KEY (product_sku) REFERENCES `MYSTORE`.`PRODUCT`(sku),	 
	 FOREIGN KEY (order_id) REFERENCES `MYSTORE`.`ORDER`(id) ON DELETE CASCADE
)ENGINE=INNODB;

