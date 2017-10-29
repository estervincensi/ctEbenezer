insert into ctebenezer.file(id, content) values (9, FILE_READ('./src/main/resources/static/photos/Ester.jpg'));
insert into ctebenezer.file(id, content) values (10, FILE_READ('./src/main/resources/static/photos/secretaria.png'));
insert into ctebenezer.file(id, content) values (11, FILE_READ('./src/main/resources/static/photos/medico.png'));
insert into ctebenezer.file(id, content) values (12, FILE_READ('./src/main/resources/static/photos/presidente.png'));

insert into ctebenezer.pessoa(id,nome,picture_id) values (1,'Ester', 9);
insert into ctebenezer.pessoa(id,nome,picture_id) values (2,'Secretária', 10);
insert into ctebenezer.pessoa(id,nome,picture_id) values (3,'Médico', 11);
insert into ctebenezer.pessoa(id,nome,picture_id) values (4,'Presidente', 12);

--AUTHENTICATION
INSERT into ctebenezer.account(id, username, password, active,pessoa_id) VALUES
(1, 'user',            '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', TRUE,1);

INSERT into ctebenezer.account(id, username, password, active,pessoa_id) VALUES 
(2, 'secretaria',            '$2a$10$6ej4z.jP7tXrIqZnvPpxXusevq8RxRuTyow.WmQLPLBaje9h1QqF.', TRUE,2);

INSERT into ctebenezer.account(id, username, password, active,pessoa_id) VALUES 
(3, 'medico',            '$2a$10$vINsh1g.xvY/75PQC3t.AuIi4h/IKgAxS8OuM9bqKSwJlfw91RIta', TRUE,3);

INSERT into ctebenezer.account(id, username, password, active,pessoa_id) VALUES 
(4, 'presidente',            '$2a$10$S8VU0PNmYJ7xZWFjXQBqTO88C7Tuj4WIjot1JvNVf2Y7jg5/1t8SW', TRUE,4);

--ROLES
insert into ctebenezer.role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_PRESIDENTE'),
(3, 'ROLE_MEDICO'),
(4, 'ROLE_ADMIN');

-- USER_ROLES
insert into ctebenezer.account_roles (accounts_id, roles_id) values
(1, 4),
(2, 1),
(4, 2),
(3, 3);

--ENDERECO
INSERT INTO CTEBENEZER.ENDERECO(ID,BAIRRO,CIDADE,NUMERO,RUA) VALUES (1,'TESTE',	'TESTE',123,'TESTE');

--RESIDENTES
insert into ctebenezer.residente (id, data_nascimento,estado_civil, naturalidade, nome, observacoes, profissao, responsavel, endereco_id,ativo,pia_ativo,rg) values (1,TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO','canoas','joão','teste','autonomo','paulo',1,false,false,123456789);
insert into ctebenezer.residente (id, data_nascimento,estado_civil, naturalidade, nome, observacoes, profissao, responsavel, endereco_id,ativo,pia_ativo,rg) values (2,TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO','porto alegre','aldair','teste','autonomo','pedro',1,true,false,987654321);

--DEPENDENCIAS
--INSERT INTO CTEBENEZER.RESIDENTE_DEPENDENCIAS (RESIDENTE_ID,DEPENDENCIAS) VALUES (1,'CRACK');
--INSERT INTO CTEBENEZER.RESIDENTE_DEPENDENCIAS (RESIDENTE_ID,DEPENDENCIAS) VALUES (2,'CRACK');

