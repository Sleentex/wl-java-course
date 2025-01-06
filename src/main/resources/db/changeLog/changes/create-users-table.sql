--liquibase formatted sql
--changeset kmorarash:create-users-table
CREATE TABLE IF NOT EXISTS hw_hibernate.users (
    id BIGINT auto_increment NOT NULL,
    email varchar(100) NOT NULL,
    phoneNumber varchar(100) DEFAULT null NULL,
    password varchar(32) NULL,
    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT users_email_unique UNIQUE KEY (email)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;

--rollback DROP TABLE users;