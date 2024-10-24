INSERT INTO users (first_name, last_name, email, password) VALUES ('user1', 'user1','user1@user1.com','$2a$10$DbIzt.DijLEiP2LhPFcGIuU3rvti6DFEHwV9.vXthnzH039I/6cyC')


INSERT INTO roles (authority) VALUES ('ROLE_SUPERADMIN')
INSERT INTO roles (authority) VALUES ('ROLE_ADMIN')
INSERT INTO roles (authority) VALUES ('ROLE_USER')

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1)

