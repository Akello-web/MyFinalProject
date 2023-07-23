CREATE TABLE t_users(

    id bigint auto_increment,
    user_age int,
    email varchar(255),
    full_name varchar(255),
    password varchar(255),
    user_money double,
    primary key (id)
);
CREATE TABLE t_permissions(
  id bigint auto_increment,
  user_role VARCHAR(255),
  primary key (id)
);

CREATE TABLE t_users_permission_list(
    user_id bigint,
    permission_list_id bigint
);

CREATE TABLE t_products(
   id bigint auto_increment,
   product_description TEXT,
   product_name varchar(255),
   product_price double,
   product_handled bit,
   category_id bigint,
   user_product_id bigint,
   primary key (id)
);

CREATE TABLE t_news(
   id bigint auto_increment,
   news_description TEXT,
   news_author varchar(255),
   news_postdate datetime(6),
   news_title varchar(255),
   primary key (id)
);

CREATE TABLE t_comments (
    id bigint auto_increment,
    commentary varchar(255),
    comment_date datetime(6),
    user_name varchar(255),
    news_id bigint,
    primary key (id)
);

CREATE TABLE t_categories(
     id bigint auto_increment,
     category_name varchar(255),
     primary key (id)
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
