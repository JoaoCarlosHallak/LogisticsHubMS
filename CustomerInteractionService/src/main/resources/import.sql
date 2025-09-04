-- USERS
INSERT INTO tb_user (id, email, cpf, username, password) VALUES (2, 'joao@email.com', '12345678901', 'joao', '$2a$12$a0CQlI3r5SHNVf.Jaun/C.lQkbfOobCWyRMC/ELfbKv71rzxgzIxS');
INSERT INTO tb_user (id, email, cpf, username, password) VALUES (3, 'maria@email.com', '98765432100', 'maria', '$2a$10$7QeGQ5Z8fH6r8o1Yp5bZCuwuQpDxqG/yVhJuqT9vqv1pHkD1F4M6W');

-- USER ROLES
INSERT INTO tb_user_roles (user_id, roles) VALUES (2, 'ROLE_CUSTOMER');
INSERT INTO tb_user_roles (user_id, roles) VALUES (3, 'ROLE_CUSTOMER');

-- ROUTES
INSERT INTO route (id, origin_name, origin_cep, destiny_name, destiny_cep) VALUES (1, 'São Paulo - SP', '01001-000', 'Rio de Janeiro - RJ', '20010-000');
INSERT INTO route (id, origin_name, origin_cep, destiny_name, destiny_cep) VALUES (2, 'Belo Horizonte - MG', '30110-002', 'Curitiba - PR', '80010-000');
INSERT INTO route (id, origin_name, origin_cep, destiny_name, destiny_cep) VALUES (3, 'Fortaleza - CE', '60025-000', 'Recife - PE', '50010-000');

-- ORDERS (ajustados para enums válidos)
INSERT INTO orders (id, name, specification, weight, route_id, state, user_id) VALUES (1, 'Pedido de Eletrônicos', 'FRAGILE', 120.5, 1, 'SEPARATION', 2);
INSERT INTO orders (id, name, specification, weight, route_id, state, user_id) VALUES (2, 'Pedido de Móveis', 'FRAGILE', 450.0, 2, 'SEPARATION', 2);
INSERT INTO orders (id, name, specification, weight, route_id, state, user_id) VALUES (3, 'Pedido de Alimentos', 'PERISHABLE', 80.0, 3, 'SEPARATION', 3);
