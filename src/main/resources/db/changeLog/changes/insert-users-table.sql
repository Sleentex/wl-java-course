--liquibase formatted sql
--changeset kmorarash:insert-users-table

INSERT INTO
    `users`
VALUES
    (1, "my@gmail.com", "+34 301 539-0605", "$StrongPassword012"),
    (2, "my.second@gmail.com", "+34 301 539-0606", "#StrongPassword013"),
    (3, "my.another@gmail.com", "+34 301 539-0607", "&StrongPassword014");

--rollback DELETE FROM users WHERE id IN(1,2,3);