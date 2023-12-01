CREATE TABLE IF NOT EXISTS `PERMISSION` (

    `permission_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    `permission_enable` boolean NOT NULL DEFAULT true
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `ROLE` (

    `role_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    `role_enable` boolean NOT NULL DEFAULT true
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `USER` (

    `user_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(100) NOT NULL,
    `last_name` varchar(100) NOT NULL,
    `username` varchar(100) NOT NULL,
    `password` varchar(30) NOT NULL,
    `email` varchar(100) NOT NULL unique,
    `account_enabled` boolean NOT NULL DEFAULT true,
    `role_id` int,
    CONSTRAINT `user_role_fk`
        FOREIGN KEY (role_id) REFERENCES ROLE(role_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `ROLE_PERMISSION` (
    `role_id` INT NOT NULL,
    `permission_id` INT NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`),
    CONSTRAINT `RolePermission_role_fk`
        FOREIGN KEY (role_id) REFERENCES ROLE(role_id),
    CONSTRAINT `RolePermission_permission_fk`
        FOREIGN KEY (permission_id) REFERENCES PERMISSION(permission_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO PERMISSION(name,description,permission_enable)  values('READ','READ', true);
INSERT INTO ROLE(name, description, role_enable) values('ADMIN', 'ADMIN', true);
INSERT INTO ROLE_PERMISSION(role_id, permission_id) values(1,1);
INSERT INTO USER(first_name, last_name, username, password, email, account_enabled, role_id)
    values('test1', 'lastTest1', 'test1', '123456', 'test1@gmail.com', true, 1);
INSERT INTO PERMISSION(name,description,permission_enable)  values('WRITE','WRITE', true);
INSERT INTO ROLE(name, description, role_enable) values('USER', 'USER', true);
INSERT INTO ROLE_PERMISSION(role_id, permission_id) values(1,2);