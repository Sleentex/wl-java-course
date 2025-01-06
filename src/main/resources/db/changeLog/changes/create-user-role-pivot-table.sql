--liquibase formatted sql
--changeset kmorarash:create-user-role-pivot-table
CREATE TABLE hw_hibernate.user_role_pivot (
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci;
CREATE UNIQUE INDEX user_role_pivot_user_id_IDX USING BTREE ON hw_hibernate.user_role_pivot (user_id,role_id);

--rollback DROP TABLE user_role_pivot;