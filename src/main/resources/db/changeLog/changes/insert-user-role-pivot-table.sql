--liquibase formatted sql
--changeset kmorarash:insert-user-role-pivot-table

INSERT INTO
    `user_role_pivot`
VALUES
    (1, 1),
    (2, 2);

--rollback DELETE FROM user_role_pivot WHERE (user_id = 1 AND role_id = 1) AND (user_id = 2 AND role_id = 2);