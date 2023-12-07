drop database AcademiaBDD;
create database AcademiaBDD;
use AcademiaBDD;
create table Clientes(
	cpf varchar(13),
  	ficha_corporal varchar(200),
	nome varchar(50) not null,
	cep varchar(8),
	estado varchar(100),
	cidade varchar(100),
	bairro varchar(30),
  	rua varchar(30),
  	numero varchar(30),
  	constraint CPF_PK primary key(cpf),
    constraint NOME_UK unique(nome)
);
create table TELEFONE_CLIENTES(
	CPF_CLIENTE varchar(13),
  	telefone varchar(50),
	constraint FONECLIENTE_PK primary key(CPF_CLIENTE,telefone),
  	foreign key(CPF_CLIENTE) REFERENCES Clientes(cpf)
);
create table Academia(
	nome varchar(50) not null,
  	proprietario varchar(50) not null,
	CNPJ varchar(14),
	localizacao varchar(100),
	constraint CNPJ_PK PRIMARY KEY(CNPJ),
  	CONSTRAINT NOME_UK UNIQUE(nome)
);
create table Aparelho(
	qualidade varchar(30),
	preco double not null,
  	id int not null AUTO_INCREMENT,
  	marca varchar(100),
  	CNPJ_Academia varchar(14),
	constraint PRECO_CK CHECK(preco > 0),
	constraint ID_PK PRIMARY KEY(id),
  	constraint CNPJ_FK1 FOREIGN KEY(CNPJ_Academia) references Academia(CNPJ)
);
create table Clientes_usam_aparelho(
	CPF_CLIENTES varchar(13),
	ID_APARELHOS INT,
	CONSTRAINT CUA_PK PRIMARY KEY(CPF_CLIENTES, ID_APARELHOS),
	CONSTRAINT CPF_FK FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
  	CONSTRAINT ID_FK FOREIGN KEY(ID_APARELHOS) REFERENCES Aparelho(id)
);
create table Clientes_integrado_Academia(
	CPF_CLIENTES varchar(13),
	CNPJ_ACADEMIA varchar(14),
  	Data_ingresso date,
	CONSTRAINT CIA_PK PRIMARY KEY(CPF_CLIENTES, CNPJ_ACADEMIA),
	CONSTRAINT CPF_FK2 FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
	CONSTRAINT CNPJ_FK2 FOREIGN KEY(CNPJ_ACADEMIA) REFERENCES Academia(CNPJ)
);
create table Cliente_premium(
	cpf varchar(13),
	Dieta_planejada varchar(500),
	Desconto double,
    CONSTRAINT CPF_PK3 PRIMARY KEY(cpf),
	CONSTRAINT CPF_FK4 FOREIGN KEY(cpf) REFERENCES Clientes(cpf)
	
);
create table Funcionario(
	nome varchar(50) not null,
	cpf varchar(13),
	funcao varchar(30) not null,
	salario double not null,
  	data_nascimento date,
  	CNPJ_Academia varchar(14),
	CONSTRAINT SALARIO_CK check(salario > 600),
	CONSTRAINT FUNCIONARIO_PK PRIMARY KEY(cpf),
	CONSTRAINT CNPJ_FK3 FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
create table Dependente_funcionario(
	nome varchar(50) not null,
	data_nascimento date,
	cpf_dependente varchar(13),
	cpf_funcionario varchar(13),
  	grau_parentesco varchar(30) not null,
	CONSTRAINT DEPENDENTE_PK PRIMARY KEY(cpf_dependente, cpf_funcionario),
	CONSTRAINT FUNCIONARIO_FK FOREIGN KEY(cpf_funcionario) REFERENCES Funcionario(cpf)
);
create table Loja_suplemento(
	CNPJ varchar(14),
	localizacao varchar(50) not null,
	proprietario varchar(50) not null,
	CONSTRAINT LOJA_PK PRIMARY KEY(CNPJ)
);
create table produtos(
	CNPJ_LOJA varchar(14),
	produto varchar(50),
	CONSTRAINT produtos_PK PRIMARY KEY(CNPJ_LOJA, produto),
	CONSTRAINT CNPJ_FK4 FOREIGN KEY(CNPJ_LOJA) REFERENCES Loja_suplemento(CNPJ)
);
create table Academia_tem_loja(
	CNPJ_Academia varchar(14),
	CNPJ_Loja varchar(14),
	CONSTRAINT ATL_PK PRIMARY KEY(CNPJ_Academia, CNPJ_Loja),
	CONSTRAINT LOJA_FK FOREIGN KEY(CNPJ_Loja) REFERENCES Loja_suplemento(CNPJ),
	CONSTRAINT ACADEMIA_FK FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
-- Inserção
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678944', '75cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camargo', 'Uppertown', 'Main Street', '111');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678945', '45cm-Braço,70cm-panturrilha,190cm-toráx', 'John Casmurro', 'Maracatu', 'Carlos X', '112');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678946', '78cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camavinga', 'Uberlandia', 'Street 1', '113');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678947', '59cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camuario', 'Birigui', 'Street 2', '114');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678948', '53cm-Braço,70cm-panturrilha,190cm-toráx', 'John Bisnaguinha', 'Sanka', 'Street 3', '115');


INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1234');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678945', '555-1235');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678946', '555-1236');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1237');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1238');


INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Smart Fit', 'James Bond', '12345678901234', 'Fitness main, rua alveredo 201');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Ipanema Fit', 'Messi', '12345678901235', 'Park moon, rua Monlaike 567');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Dumb Fit', 'Cristiano Ronaldo', '12345678901236', 'Disney, rua marney 221');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Sky Fit', 'Gumball', '12345678901237', 'Avenida Tadeu, rua carlinho 101');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Grow Fit', 'Martinelli', '12345678901238', 'Alvorado, rua alfredo 220');

INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 500.00, 1, '12345678901234', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 550.00, 2, '12345678901235', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Baixa qualidade', 300.00, 3, '12345678901236', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 400.00, 4, '12345678901237', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 700.00, 5, '12345678901238', "Banana");

INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678944', 1);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678945', 2);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678946', 3);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678947', 4);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678948', 5);


INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678944', '12345678901234', '2022-01-15');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678945', '12345678901234', '2022-01-16');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678946', '12345678901234', '2022-01-17');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678947', '12345678901235', '2022-01-18');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678948', '12345678901235', '2022-01-19');

INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678944', 'Café da manhã-11 ovos cozidos,
						Almoço-400 gramas de carne e 1 kg de arroz,
                        Café da tarde- 850 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 9.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678945', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678946', 'Café da manhã-24 ovos cozidos,
						Almoço-320 gramas de carne e 1 kg de arroz,
                        Café da tarde- 830 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678947', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 500 gramas de iorgute,
                        Janta-200 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678948', 'Café da manhã-22 ovos cozidos,
						Almoço-100 gramas de carne e 100 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 11.0);


INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Neymar', '98765432109', 'Personal Trainer', 1500.00, '1990-05-10', '12345678901234');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('José', '98765432110', 'Personal Trainer', 1750.00, '1995-05-19', '12345678901235');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Josimar', '98765432111', 'Faxineiro', 2500.00, '1987-06-13', '12345678901236');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Reinaldo', '98765432112', 'Técnico em informatica', 3000.00, '1993-11-27', '12345678901237');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Joaninho', '98765432113', 'Trabalhos gerais', 2000.00, '1990-12-30', '12345678901238');

INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Steve Rogers', '2010-03-20', '78901234567', '98765432109', 'Primo');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Marquinhos Rogers', '2011-02-25', '78901234568', '98765432109', 'Irmão');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Bartolomeu Rogers', '2012-04-22', '78901234569', '98765432109', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Ronaldo Marcos', '2015-05-21', '78901234570', '98765432110', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Champions Marcos', '2017-03-22', '78901234571', '98765432110', 'Pai');




INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345676', 'Supplement Street, rua Street-200', 'Cbum');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345678', 'Spartan suplement, rua Manevaldo-201', 'Ramon');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345671', 'Monster, rua dosnor-198', 'Alisha Leehman');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345679', 'Bodybuilder, rua monstro-199', 'Angelina Jolie');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345675', 'Monster, rua palmeiras', 'Julião');

INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345676', 'Whey Protein');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345678', 'Creatina');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345671', 'Hipercalórico');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345679', 'Pré-treino');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345675', 'Durateston');


INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345671');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901236', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345679');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345678');
SELECT * FROM Clientes;




drop database AcademiaBDD;
create database AcademiaBDD;
use AcademiaBDD;
create table Clientes(
	cpf varchar(13),
  	ficha_corporal varchar(200),
	nome varchar(50) not null,
	cep varchar(8),
	estado varchar(100),
	cidade varchar(100),
	bairro varchar(30),
  	rua varchar(30),
  	numero varchar(30),
  	constraint CPF_PK primary key(cpf),
    constraint NOME_UK unique(nome)
);
create table TELEFONE_CLIENTES(
	CPF_CLIENTE varchar(13),
  	telefone varchar(50),
	constraint FONECLIENTE_PK primary key(CPF_CLIENTE,telefone),
  	foreign key(CPF_CLIENTE) REFERENCES Clientes(cpf)
);
create table Academia(
	nome varchar(50) not null,
  	proprietario varchar(50) not null,
	CNPJ varchar(14),
	localizacao varchar(100),
	constraint CNPJ_PK PRIMARY KEY(CNPJ),
  	CONSTRAINT NOME_UK UNIQUE(nome)
);
create table Aparelho(
	qualidade varchar(30),
	preco double not null,
  	id int not null AUTO_INCREMENT,
  	marca varchar(100),
  	CNPJ_Academia varchar(14),
	constraint PRECO_CK CHECK(preco > 0),
	constraint ID_PK PRIMARY KEY(id),
  	constraint CNPJ_FK1 FOREIGN KEY(CNPJ_Academia) references Academia(CNPJ)
);
create table Clientes_usam_aparelho(
	CPF_CLIENTES varchar(13),
	ID_APARELHOS INT,
	CONSTRAINT CUA_PK PRIMARY KEY(CPF_CLIENTES, ID_APARELHOS),
	CONSTRAINT CPF_FK FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
  	CONSTRAINT ID_FK FOREIGN KEY(ID_APARELHOS) REFERENCES Aparelho(id)
);
create table Clientes_integrado_Academia(
	CPF_CLIENTES varchar(13),
	CNPJ_ACADEMIA varchar(14),
  	Data_ingresso date,
	CONSTRAINT CIA_PK PRIMARY KEY(CPF_CLIENTES, CNPJ_ACADEMIA),
	CONSTRAINT CPF_FK2 FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
	CONSTRAINT CNPJ_FK2 FOREIGN KEY(CNPJ_ACADEMIA) REFERENCES Academia(CNPJ)
);
create table Cliente_premium(
	cpf varchar(13),
	Dieta_planejada varchar(500),
	Desconto double,
    CONSTRAINT CPF_PK3 PRIMARY KEY(cpf),
	CONSTRAINT CPF_FK4 FOREIGN KEY(cpf) REFERENCES Clientes(cpf)
	
);
create table Funcionario(
	nome varchar(50) not null,
	cpf varchar(13),
	funcao varchar(30) not null,
	salario double not null,
  	data_nascimento date,
  	CNPJ_Academia varchar(14),
	CONSTRAINT SALARIO_CK check(salario > 600),
	CONSTRAINT FUNCIONARIO_PK PRIMARY KEY(cpf),
	CONSTRAINT CNPJ_FK3 FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
create table Dependente_funcionario(
	nome varchar(50) not null,
	data_nascimento date,
	cpf_dependente varchar(13),
	cpf_funcionario varchar(13),
  	grau_parentesco varchar(30) not null,
	CONSTRAINT DEPENDENTE_PK PRIMARY KEY(cpf_dependente, cpf_funcionario),
	CONSTRAINT FUNCIONARIO_FK FOREIGN KEY(cpf_funcionario) REFERENCES Funcionario(cpf)
);
create table Loja_suplemento(
	CNPJ varchar(14),
	localizacao varchar(50) not null,
	proprietario varchar(50) not null,
	CONSTRAINT LOJA_PK PRIMARY KEY(CNPJ)
);
create table produtos(
	CNPJ_LOJA varchar(14),
	produto varchar(50),
	CONSTRAINT produtos_PK PRIMARY KEY(CNPJ_LOJA, produto),
	CONSTRAINT CNPJ_FK4 FOREIGN KEY(CNPJ_LOJA) REFERENCES Loja_suplemento(CNPJ)
);
create table Academia_tem_loja(
	CNPJ_Academia varchar(14),
	CNPJ_Loja varchar(14),
	CONSTRAINT ATL_PK PRIMARY KEY(CNPJ_Academia, CNPJ_Loja),
	CONSTRAINT LOJA_FK FOREIGN KEY(CNPJ_Loja) REFERENCES Loja_suplemento(CNPJ),
	CONSTRAINT ACADEMIA_FK FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
-- Inserção
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678944', '75cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camargo', 'Uppertown', 'Main Street', '111');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678945', '45cm-Braço,70cm-panturrilha,190cm-toráx', 'John Casmurro', 'Maracatu', 'Carlos X', '112');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678946', '78cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camavinga', 'Uberlandia', 'Street 1', '113');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678947', '59cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camuario', 'Birigui', 'Street 2', '114');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678948', '53cm-Braço,70cm-panturrilha,190cm-toráx', 'John Bisnaguinha', 'Sanka', 'Street 3', '115');


INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1234');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678945', '555-1235');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678946', '555-1236');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1237');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1238');


INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Smart Fit', 'James Bond', '12345678901234', 'Fitness main, rua alveredo 201');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Ipanema Fit', 'Messi', '12345678901235', 'Park moon, rua Monlaike 567');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Dumb Fit', 'Cristiano Ronaldo', '12345678901236', 'Disney, rua marney 221');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Sky Fit', 'Gumball', '12345678901237', 'Avenida Tadeu, rua carlinho 101');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Grow Fit', 'Martinelli', '12345678901238', 'Alvorado, rua alfredo 220');

INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 500.00, 1, '12345678901234', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 550.00, 2, '12345678901235', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Baixa qualidade', 300.00, 3, '12345678901236', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 400.00, 4, '12345678901237', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 700.00, 5, '12345678901238', "Banana");

INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678944', 1);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678945', 2);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678946', 3);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678947', 4);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678948', 5);


INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678944', '12345678901234', '2022-01-15');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678945', '12345678901234', '2022-01-16');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678946', '12345678901234', '2022-01-17');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678947', '12345678901235', '2022-01-18');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678948', '12345678901235', '2022-01-19');

INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678944', 'Café da manhã-11 ovos cozidos,
						Almoço-400 gramas de carne e 1 kg de arroz,
                        Café da tarde- 850 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 9.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678945', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678946', 'Café da manhã-24 ovos cozidos,
						Almoço-320 gramas de carne e 1 kg de arroz,
                        Café da tarde- 830 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678947', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 500 gramas de iorgute,
                        Janta-200 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678948', 'Café da manhã-22 ovos cozidos,
						Almoço-100 gramas de carne e 100 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 11.0);


INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Neymar', '98765432109', 'Personal Trainer', 1500.00, '1990-05-10', '12345678901234');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('José', '98765432110', 'Personal Trainer', 1750.00, '1995-05-19', '12345678901235');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Josimar', '98765432111', 'Faxineiro', 2500.00, '1987-06-13', '12345678901236');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Reinaldo', '98765432112', 'Técnico em informatica', 3000.00, '1993-11-27', '12345678901237');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Joaninho', '98765432113', 'Trabalhos gerais', 2000.00, '1990-12-30', '12345678901238');

INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Steve Rogers', '2010-03-20', '78901234567', '98765432109', 'Primo');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Marquinhos Rogers', '2011-02-25', '78901234568', '98765432109', 'Irmão');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Bartolomeu Rogers', '2012-04-22', '78901234569', '98765432109', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Ronaldo Marcos', '2015-05-21', '78901234570', '98765432110', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Champions Marcos', '2017-03-22', '78901234571', '98765432110', 'Pai');




INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345676', 'Supplement Street, rua Street-200', 'Cbum');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345678', 'Spartan suplement, rua Manevaldo-201', 'Ramon');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345671', 'Monster, rua dosnor-198', 'Alisha Leehman');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345679', 'Bodybuilder, rua monstro-199', 'Angelina Jolie');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345675', 'Monster, rua palmeiras', 'Julião');

INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345676', 'Whey Protein');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345678', 'Creatina');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345671', 'Hipercalórico');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345679', 'Pré-treino');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345675', 'Durateston');


INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345671');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901236', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345679');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345678');
SELECT * FROM Clientes;




drop database AcademiaBDD;
create database AcademiaBDD;
use AcademiaBDD;
create table Clientes(
	cpf varchar(13),
  	ficha_corporal varchar(200),
	nome varchar(50) not null,
	cep varchar(8),
	estado varchar(100),
	cidade varchar(100),
	bairro varchar(30),
  	rua varchar(30),
  	numero varchar(30),
  	constraint CPF_PK primary key(cpf),
    constraint NOME_UK unique(nome)
);
create table TELEFONE_CLIENTES(
	CPF_CLIENTE varchar(13),
  	telefone varchar(50),
	constraint FONECLIENTE_PK primary key(CPF_CLIENTE,telefone),
  	foreign key(CPF_CLIENTE) REFERENCES Clientes(cpf)
);
create table Academia(
	nome varchar(50) not null,
  	proprietario varchar(50) not null,
	CNPJ varchar(14),
	localizacao varchar(100),
	constraint CNPJ_PK PRIMARY KEY(CNPJ),
  	CONSTRAINT NOME_UK UNIQUE(nome)
);
create table Aparelho(
	qualidade varchar(30),
	preco double not null,
  	id int not null AUTO_INCREMENT,
  	marca varchar(100),
  	CNPJ_Academia varchar(14),
	constraint PRECO_CK CHECK(preco > 0),
	constraint ID_PK PRIMARY KEY(id),
  	constraint CNPJ_FK1 FOREIGN KEY(CNPJ_Academia) references Academia(CNPJ)
);
create table Clientes_usam_aparelho(
	CPF_CLIENTES varchar(13),
	ID_APARELHOS INT,
	CONSTRAINT CUA_PK PRIMARY KEY(CPF_CLIENTES, ID_APARELHOS),
	CONSTRAINT CPF_FK FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
  	CONSTRAINT ID_FK FOREIGN KEY(ID_APARELHOS) REFERENCES Aparelho(id)
);
create table Clientes_integrado_Academia(
	CPF_CLIENTES varchar(13),
	CNPJ_ACADEMIA varchar(14),
  	Data_ingresso date,
	CONSTRAINT CIA_PK PRIMARY KEY(CPF_CLIENTES, CNPJ_ACADEMIA),
	CONSTRAINT CPF_FK2 FOREIGN KEY(CPF_CLIENTES) REFERENCES Clientes(cpf),
	CONSTRAINT CNPJ_FK2 FOREIGN KEY(CNPJ_ACADEMIA) REFERENCES Academia(CNPJ)
);
create table Cliente_premium(
	cpf varchar(13),
	Dieta_planejada varchar(500),
	Desconto double,
    CONSTRAINT CPF_PK3 PRIMARY KEY(cpf),
	CONSTRAINT CPF_FK4 FOREIGN KEY(cpf) REFERENCES Clientes(cpf)
	
);
create table Funcionario(
	nome varchar(50) not null,
	cpf varchar(13),
	funcao varchar(30) not null,
	salario double not null,
  	data_nascimento date,
  	CNPJ_Academia varchar(14),
	CONSTRAINT SALARIO_CK check(salario > 600),
	CONSTRAINT FUNCIONARIO_PK PRIMARY KEY(cpf),
	CONSTRAINT CNPJ_FK3 FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
create table Dependente_funcionario(
	nome varchar(50) not null,
	data_nascimento date,
	cpf_dependente varchar(13),
	cpf_funcionario varchar(13),
  	grau_parentesco varchar(30) not null,
	CONSTRAINT DEPENDENTE_PK PRIMARY KEY(cpf_dependente, cpf_funcionario),
	CONSTRAINT FUNCIONARIO_FK FOREIGN KEY(cpf_funcionario) REFERENCES Funcionario(cpf)
);
create table Loja_suplemento(
	CNPJ varchar(14),
	localizacao varchar(50) not null,
	proprietario varchar(50) not null,
	CONSTRAINT LOJA_PK PRIMARY KEY(CNPJ)
);
create table produtos(
	CNPJ_LOJA varchar(14),
	produto varchar(50),
	CONSTRAINT produtos_PK PRIMARY KEY(CNPJ_LOJA, produto),
	CONSTRAINT CNPJ_FK4 FOREIGN KEY(CNPJ_LOJA) REFERENCES Loja_suplemento(CNPJ)
);
create table Academia_tem_loja(
	CNPJ_Academia varchar(14),
	CNPJ_Loja varchar(14),
	CONSTRAINT ATL_PK PRIMARY KEY(CNPJ_Academia, CNPJ_Loja),
	CONSTRAINT LOJA_FK FOREIGN KEY(CNPJ_Loja) REFERENCES Loja_suplemento(CNPJ),
	CONSTRAINT ACADEMIA_FK FOREIGN KEY(CNPJ_Academia) REFERENCES Academia(CNPJ)
);
-- Inserção
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678944', '75cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camargo', 'Uppertown', 'Main Street', '111');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678945', '45cm-Braço,70cm-panturrilha,190cm-toráx', 'John Casmurro', 'Maracatu', 'Carlos X', '112');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678946', '78cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camavinga', 'Uberlandia', 'Street 1', '113');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678947', '59cm-Braço,70cm-panturrilha,190cm-toráx', 'John Camuario', 'Birigui', 'Street 2', '114');
INSERT INTO Clientes (cpf, ficha_corporal, nome, bairro, rua, numero)
VALUES ('12345678948', '53cm-Braço,70cm-panturrilha,190cm-toráx', 'John Bisnaguinha', 'Sanka', 'Street 3', '115');


INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1234');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678945', '555-1235');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678946', '555-1236');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1237');
INSERT INTO TELEFONE_CLIENTES (CPF_CLIENTE, telefone) VALUES ('12345678944', '555-1238');


INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Smart Fit', 'James Bond', '12345678901234', 'Fitness main, rua alveredo 201');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Ipanema Fit', 'Messi', '12345678901235', 'Park moon, rua Monlaike 567');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Dumb Fit', 'Cristiano Ronaldo', '12345678901236', 'Disney, rua marney 221');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Sky Fit', 'Gumball', '12345678901237', 'Avenida Tadeu, rua carlinho 101');
INSERT INTO Academia (nome, proprietario, CNPJ, localizacao)
VALUES ('Grow Fit', 'Martinelli', '12345678901238', 'Alvorado, rua alfredo 220');

INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 500.00, 1, '12345678901234', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 550.00, 2, '12345678901235', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Baixa qualidade', 300.00, 3, '12345678901236', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Media qualidade', 400.00, 4, '12345678901237', "Banana");
INSERT INTO Aparelho (qualidade, preco, id, CNPJ_Academia, marca)
VALUES ('Alta qualidade', 700.00, 5, '12345678901238', "Banana");

INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678944', 1);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678945', 2);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678946', 3);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678947', 4);
INSERT INTO Clientes_usam_aparelho (CPF_CLIENTES, ID_APARELHOS)
VALUES ('12345678948', 5);


INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678944', '12345678901234', '2022-01-15');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678945', '12345678901234', '2022-01-16');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678946', '12345678901234', '2022-01-17');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678947', '12345678901235', '2022-01-18');
INSERT INTO Clientes_integrado_Academia (CPF_CLIENTES, CNPJ_ACADEMIA, Data_ingresso)
VALUES ('12345678948', '12345678901235', '2022-01-19');

INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678944', 'Café da manhã-11 ovos cozidos,
						Almoço-400 gramas de carne e 1 kg de arroz,
                        Café da tarde- 850 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 9.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678945', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678946', 'Café da manhã-24 ovos cozidos,
						Almoço-320 gramas de carne e 1 kg de arroz,
                        Café da tarde- 830 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678947', 'Café da manhã-22 ovos cozidos,
						Almoço-300 gramas de carne e 1 kg de arroz,
                        Café da tarde- 500 gramas de iorgute,
                        Janta-200 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 10.0);
INSERT INTO Cliente_premium (cpf, Dieta_planejada, Desconto)
VALUES ('12345678948', 'Café da manhã-22 ovos cozidos,
						Almoço-100 gramas de carne e 100 kg de arroz,
                        Café da tarde- 800 gramas de iorgute,
                        Janta-500 gramas de carne, 200 gramas de legumes e 1kg de peixe,
                        

', 11.0);


INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Neymar', '98765432109', 'Personal Trainer', 1500.00, '1990-05-10', '12345678901234');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('José', '98765432110', 'Personal Trainer', 1750.00, '1995-05-19', '12345678901235');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Josimar', '98765432111', 'Faxineiro', 2500.00, '1987-06-13', '12345678901236');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Reinaldo', '98765432112', 'Técnico em informatica', 3000.00, '1993-11-27', '12345678901237');
INSERT INTO Funcionario (nome, cpf, funcao, salario, data_nascimento, CNPJ_Academia)
VALUES ('Joaninho', '98765432113', 'Trabalhos gerais', 2000.00, '1990-12-30', '12345678901238');

INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Steve Rogers', '2010-03-20', '78901234567', '98765432109', 'Primo');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Marquinhos Rogers', '2011-02-25', '78901234568', '98765432109', 'Irmão');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Bartolomeu Rogers', '2012-04-22', '78901234569', '98765432109', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Ronaldo Marcos', '2015-05-21', '78901234570', '98765432110', 'Filho');
INSERT INTO Dependente_funcionario (nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco)
VALUES ('Champions Marcos', '2017-03-22', '78901234571', '98765432110', 'Pai');




INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345676', 'Supplement Street, rua Street-200', 'Cbum');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345678', 'Spartan suplement, rua Manevaldo-201', 'Ramon');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345671', 'Monster, rua dosnor-198', 'Alisha Leehman');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345679', 'Bodybuilder, rua monstro-199', 'Angelina Jolie');
INSERT INTO Loja_suplemento (CNPJ, localizacao, proprietario)
VALUES ('56789012345675', 'Monster, rua palmeiras', 'Julião');

INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345676', 'Whey Protein');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345678', 'Creatina');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345671', 'Hipercalórico');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345679', 'Pré-treino');
INSERT INTO produtos (CNPJ_LOJA, produto)
VALUES ('56789012345675', 'Durateston');


INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345671');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901236', '56789012345678');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901234', '56789012345679');
INSERT INTO Academia_tem_loja (CNPJ_Academia, CNPJ_Loja)
VALUES ('12345678901235', '56789012345678');
SELECT * FROM Clientes;




