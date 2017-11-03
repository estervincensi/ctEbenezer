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
INSERT INTO CTEBENEZER.RESIDENTE(ID, ATIVO, DATA_NASCIMENTO, ESTADO_CIVIL, NATURALIDADE, NOME, OBSERVACOES, PIA_ATIVO, PROFISSAO, RESPONSAVEL, RG, TELEFONE, ENDERECO_ID) VALUES
(1, TRUE, TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO', 'canoas', STRINGDECODE('jo\u00e3o'), 'teste', TRUE, 'autonomo', 'paulo', '123456789', '123456789', 1),
(2, TRUE, TIMESTAMP '1995-04-02 00:00:00.0', 'CASADO', 'porto alegre', 'aldair', STRINGDECODE('teste\nData de entrada = 2017-11-02 17:30:00.048/Data de Sa\u00edda:Thu Nov 02 17:34:47 BRST 2017\n Data de entrada:Thu Nov 02 17:56:31 BRST 2017'), TRUE, 'autonomo', 'pedro', '987654321', '987654321', 1),
(3, TRUE, TIMESTAMP '2017-11-02 17:13:10.91', 'CASADO', 'cachoeirinha', 'jose', 'teste', TRUE, 'abc', 'pedro', '65498725', '258963147', 1),
(4, TRUE, TIMESTAMP '2017-11-02 17:15:05.011', 'CASADO', 'cachoeirinha', 'pedro', 'teste', TRUE, 'abc', 'pedro', '65498725', '259632154', 1),
(5, TRUE, TIMESTAMP '2017-11-02 17:15:08.411', 'CASADO', 'cachoeirinha', 'paulo', 'teste', TRUE, 'abc', 'pedro', '6543214', '859621475', 1),
(6, TRUE, TIMESTAMP '2017-11-02 17:15:12.658', 'CASADO', 'cachoeirinha', 'pafuncio', 'teste', TRUE, 'abc', 'pedro', '84549843', '1236548256', 1),
(7, FALSE, TIMESTAMP '2017-11-02 17:15:16.308', 'CASADO', 'cachoeirinha', 'irineu', STRINGDECODE('teste\r\nData de entrada = 2017-11-02 17:30:35.983/Data de Sa\u00edda:Thu Nov 02 17:53:31 BRST 2017'), FALSE, 'abc', 'pedro', '41464687897', '63125448618', 1);  

--PIA
INSERT INTO CTEBENEZER.PIA(ID, ATIVO, AVALIACAO_FINAL, AVALIACAO_INICIAL, DATA_ENTRADA, DATA_SAIDA, DESISTIU, PROTECAO_JUDICIAL, TEMPO_PREVISTO, VIVEU_NA_RUA, RESIDENTE_ID) VALUES
(1, FALSE, 'desistiu', 'teste', TIMESTAMP '2017-11-02 17:30:00.048', TIMESTAMP '2017-11-02 17:34:47.699', TRUE, FALSE, '6', FALSE, 2),
(2, FALSE, 'teste', 'teste', TIMESTAMP '2017-11-02 17:30:35.983', TIMESTAMP '2017-11-02 17:53:31.469', FALSE, FALSE, '9', FALSE, 7),
(3, TRUE, NULL, 'teste teste', TIMESTAMP '2017-11-02 17:31:18.707', NULL, FALSE, FALSE, '12', FALSE, 3),
(4, TRUE, NULL, 'teste teste', TIMESTAMP '2017-11-02 17:31:47.045', NULL, FALSE, FALSE, '123456', FALSE, 1),
(5, TRUE, NULL, 'teste', TIMESTAMP '2017-11-02 17:32:20.554', NULL, FALSE, FALSE, '12', FALSE, 6),
(6, TRUE, NULL, 'teste', TIMESTAMP '2017-11-02 17:33:09.112', NULL, FALSE, FALSE, '6', FALSE, 5),
(7, TRUE, NULL, 'teste123', TIMESTAMP '2017-11-02 17:33:27.752', NULL, FALSE, FALSE, '5', FALSE, 4),
(8, TRUE, NULL, 'teste', TIMESTAMP '2017-11-02 17:56:41.214', NULL, FALSE, FALSE, '12', FALSE, 2); 
--DEPENDENCIAS
INSERT INTO CTEBENEZER.PIA_DEPENDENCIAS(PIA_ID, DEPENDENCIAS) VALUES
(1, 'ALCOOL'),
(1, 'COCAINA'),
(1, 'CRACK'),
(1, 'HEROINA'),
(2, 'ALCOOL'),
(2, 'COCAINA'),
(3, 'COCAINA'),
(3, 'CRACK'),
(3, 'HEROINA'),
(4, 'ALCOOL'),
(4, 'COCAINA'),
(5, 'CRACK'),
(5, 'HEROINA'),
(5, 'MACONHA'),
(6, 'ALCOOL'),
(6, 'COCAINA'),
(6, 'CRACK'),
(6, 'HEROINA'),
(6, 'MACONHA'),
(7, 'ALCOOL'),
(7, 'COCAINA'),
(7, 'CRACK'),
(7, 'HEROINA'),
(7, 'MACONHA'),
(7, 'MERLA'),
(8, 'ALCOOL'),
(8, 'COCAINA'),
(8, 'CRACK');     

--APTIDOES
INSERT INTO CTEBENEZER.PIA_APTIDOES(PIA_ID, APTIDOES) VALUES
(2, STRINGDECODE('m\u00fasico-cantor')),
(2, STRINGDECODE('m\u00fasico-instrumentista')),
(3, STRINGDECODE('m\u00fasico-instrumentista')),
(3, 'artesanato'),
(4, STRINGDECODE('m\u00fasico-instrumentista')),
(4, 'artesanato'),
(5, 'jardinagem'),
(5, 'teatro'),
(6, STRINGDECODE('m\u00fasico-instrumentista')),
(6, 'artesanato'),
(6, 'jardinagem'),
(7, STRINGDECODE('m\u00fasico-instrumentista')),
(7, 'artesanato'),
(7, 'jardinagem'),
(7, 'teatro'),
(8, STRINGDECODE('m\u00fasico-instrumentista')),
(8, 'artesanato'),
(8, 'jardinagem'); 
