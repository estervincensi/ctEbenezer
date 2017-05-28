--AUTHENTICATION
INSERT into ctebenezer.account(id, username, password, active) VALUES
(1, 'user',            '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', TRUE);
--ROLES
insert into ctebenezer.role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into ctebenezer.account_roles (accounts_id, roles_id) values
(1, 1),
(1, 2);