INSERT INTO t_permissions (user_role)
VALUES
    ('ROLE_USER'),
    ('ROLE_SELLER'),
    ('ROLE_ADMIN');

INSERT INTO t_users (user_age, email, full_name, password, user_money)
VALUES
    (25, 'Aklbktglv@gmail.com', 'Akyl Bakhtygaliev', '$2a$10$KHsns/MZ.OvdV9t/GYrJ0eUXswM.hkzIMhCEWIXsDos8b3MHYOqbu', 500),
    (22, 'Seller@gmail.com', 'Trusted Seller', '$2a$10$wYDcjGi0.KM2xvNmwvRie.7L5cyYobIiKt9PUBk2QHAVuOYwHbvf.', 350),
    (23, 'DefaultBeka@gmail.com', 'Default Beka', '$2a$10$Tu1iSoZzWw4dZCZSqcjefuvqgJjs//Jagv6Gx4FZmjdi7upcvSgJ2', 150);

INSERT INTO t_users_permission_list (user_id, permission_list_id)
VALUES
    (1,1),
    (1,2),
    (1,3),
    (2,1),
    (2,2),
    (3,1);

INSERT INTO t_categories (category_name)
VALUES
    ('DEVICES'),
    ('FURNITURE'),
    ('CLOTHES'),
    ('BOOKS');