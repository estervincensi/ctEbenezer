insert into ctebenezer.file(id, content) values (10, FILE_READ('./src/main/resources/static/photos/Ester.jpg'));

insert into ctebenezer.pessoa(id,nome,picture_id) values (1,'Ester Vincensi', 10);

--AUTHENTICATION
INSERT into ctebenezer.account(id, username, password, active,pessoa_id) VALUES
(1, 'user',            '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', TRUE,1);
--ROLES
insert into ctebenezer.role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into ctebenezer.account_roles (accounts_id, roles_id) values
(1, 1),
(1, 2);

--ENDERECO
INSERT INTO CTEBENEZER.ENDERECO(ID,BAIRRO,CIDADE,NUMERO,RUA) VALUES (1,'TESTE',	'TESTE',123,'TESTE');

--RESIDENTES
insert into ctebenezer.residente (id, data_nascimento,estado_civil, naturalidade, nome, observacoes, profissao, responsavel, endereco_id,ativo,pia_ativo,rg) values (1,TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO','canoas','joão','teste','autonomo','paulo',1,false,false,123456789);
insert into ctebenezer.residente (id, data_nascimento,estado_civil, naturalidade, nome, observacoes, profissao, responsavel, endereco_id,ativo,pia_ativo,rg) values (2,TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO','porto alegre','aldair','teste','autonomo','pedro',1,true,false,123456789);

--DEPENDENCIAS
--INSERT INTO CTEBENEZER.RESIDENTE_DEPENDENCIAS (RESIDENTE_ID,DEPENDENCIAS) VALUES (1,'CRACK');
--INSERT INTO CTEBENEZER.RESIDENTE_DEPENDENCIAS (RESIDENTE_ID,DEPENDENCIAS) VALUES (2,'CRACK');

