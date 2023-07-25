CREATE TABLE t_users (
                         id BIGSERIAL PRIMARY KEY,
                         user_age INTEGER,
                         email VARCHAR(255),
                         full_name VARCHAR(255),
                         password VARCHAR(255),
                         user_money DOUBLE PRECISION
);

CREATE TABLE t_permissions (
                               id BIGSERIAL PRIMARY KEY,
                               user_role VARCHAR(255)
);


CREATE TABLE t_users_permission_list (
                                         user_id BIGINT,
                                         permission_list_id BIGINT
);


CREATE TABLE t_products (
                            id BIGSERIAL PRIMARY KEY,
                            product_description TEXT,
                            product_name VARCHAR(255),
                            product_price DOUBLE PRECISION,
                            product_handled BOOLEAN,
                            category_id BIGINT,
                            user_product_id BIGINT
);


CREATE TABLE t_news (
                        id BIGSERIAL PRIMARY KEY,
                        news_description TEXT,
                        news_author VARCHAR(255),
                        news_postdate TIMESTAMP(6),
                        news_title VARCHAR(255)
);


CREATE TABLE t_comments (
                            id BIGSERIAL PRIMARY KEY,
                            commentary VARCHAR(255),
                            comment_date TIMESTAMP(6),
                            user_name VARCHAR(255),
                            news_id BIGINT
);


CREATE TABLE t_categories (
                              id BIGSERIAL PRIMARY KEY,
                              category_name VARCHAR(255)
);

ALTER TABLE t_users_permission_list
    ADD CONSTRAINT fk_t_users_permissions_t_users
        FOREIGN KEY (user_id)
            REFERENCES t_users (id)
            ON DELETE CASCADE;

ALTER TABLE t_users_permission_list
    ADD CONSTRAINT fk_t_users_permissions_t_permission
        FOREIGN KEY (permission_list_id)
            REFERENCES t_permissions (id)
            ON DELETE CASCADE;

ALTER TABLE t_comments
    ADD CONSTRAINT fk_comments_news
        FOREIGN KEY (news_id) REFERENCES t_news(id)
            ON DELETE CASCADE;

ALTER TABLE t_products
    ADD CONSTRAINT fk_products_categories
        FOREIGN KEY (category_id) REFERENCES t_categories(id)
            ON DELETE CASCADE;

ALTER TABLE t_products
    ADD CONSTRAINT fk_products_users
        FOREIGN KEY (user_product_id) REFERENCES t_users(id)
            ON DELETE CASCADE;
