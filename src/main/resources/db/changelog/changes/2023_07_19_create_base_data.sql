INSERT INTO t_permissions (user_role)
VALUES
    ('ROLE_USER'),
    ('ROLE_SELLER'),
    ('ROLE_ADMIN');

INSERT INTO t_users (user_age, email, full_name, password, user_money)
VALUES
    (25, 'Aklbktglv@gmail.com', 'Akyl Bakhtygaliev', '$2a$10$KHsns/MZ.OvdV9t/GYrJ0eUXswM.hkzIMhCEWIXsDos8b3MHYOqbu', 500),
    (22, 'Seller@gmail.com', 'Trusted_Seller', '$2a$10$fCbbp7lJ2qahXuV8eknTJOr1Psr7eDHIS28ak8OY/bc1CtxEBqzou', 350),
    (23, 'Beka@gmail.com', 'Default Beka', '$2a$10$Yjo25xs.gn/MMMoewFfMLOHBYWLXOJkICNgLMThXuT3zDvGBic6oK', 150);

INSERT INTO t_users_permission_list (user_id, permission_list_id)
VALUES
    (1,1),
    (1,2),
    (1,3),
    (2,1),
    (2,2),
    (3,1);