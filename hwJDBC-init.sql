CREATE SCHEMA IF NOT EXISTS `hw_jdbc`;

USE `hw_jdbc`;

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(50) NOT NULL,
    phone    VARCHAR(20),
    password VARCHAR(20) NOT NULL
    );

GRANT ALL PRIVILEGES ON hw_jdbc.* TO 'user'@'%';
