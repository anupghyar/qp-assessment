CREATE TABLE IF NOT EXISTS `GROCERY_ITEM` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    `price` float NOT NULL,
    `stock` INT NOT NULL,
    `active` boolean DEFAULT true
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

