CREATE TABLE IF NOT EXISTS `ORDER_TABLE` (

    `order_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `address` varchar(500) NOT NULL,
    `status` varchar(20) NOT NULL,
    `order_date` date NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

ALTER TABLE defaultdb.GROCERY_ITEM ADD order_id INT;
ALTER TABLE defaultdb.GROCERY_ITEM ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES defaultdb.ORDER_TABLE(order_id);
