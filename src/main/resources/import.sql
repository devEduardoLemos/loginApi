INSERT INTO users (first_name, last_name, email, password) VALUES ('Eduardo', 'Lemos','eduardo.lemos@inovector3d.com.br','')
INSERT INTO users (first_name, last_name, email, password) VALUES ('Danilo', 'Ribeiro','danilo.ribeiro@inovector3d.com.br','')
INSERT INTO users (first_name, last_name, email, password) VALUES ('Isabele', 'Almeida','isabele.almeida@inovector3d.com.br','')
INSERT INTO users (first_name, last_name, email, password) VALUES ('Vinicius', 'Amorim','vinicius.amorim@inovector3d.com.br','')
INSERT INTO users (first_name, last_name, email, password) VALUES ('Ewerton', 'Larry','ewerton.larry@inovector3d.com.br','')
INSERT INTO users (first_name, last_name, email, password) VALUES ('Nutec', 'SergipeTec','nutec.sergipetec@gmail.com','')

INSERT INTO roles (authority) VALUES ('ROLE_SUPERADMIN')
INSERT INTO roles (authority) VALUES ('ROLE_ADMIN')
INSERT INTO roles (authority) VALUES ('ROLE_USER')

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1)
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1)
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1)
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2)
INSERT INTO users_roles (user_id, role_id) VALUES (5, 2)
INSERT INTO users_roles (user_id, role_id) VALUES (6, 3)