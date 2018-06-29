DROP DATABASE projetoERP;

CREATE DATABASE projetoERP DEFAULT CHARACTER SET utf8;

USE projetoERP;

CREATE TABLE cadProdutoFabricantes(
	idCadProdutoFabricante 		int(11) NOT NULL AUTO_INCREMENT,
	razaoSocial		varchar(100),
	cnpj			varchar(20),
	ie				varchar(30),
	im          	varchar(30),
	sif 			varchar(30) 	COMMENT 'SIF: Obrigatório para produtores de CARNE – BOVINO e EQUINOS, SUINO, AVES E COLEHOS, PEQUENOS RUMINANTES, OVOS , PESCADO, LEITE, MEL',
	logradouro		varchar(100),
	bairro			varchar(100),
	cidade			varchar(100),
	uf				varchar(2),
	pais			varchar(100),
	cep				varchar(10),
	telefone		varchar(20),
	contato			varchar(50),
	url				varchar(100),
	PRIMARY KEY (idCadProdutoFabricante)
	);

CREATE TABLE cadProdutoMarcas(
	idCadProdutoMarca 		int(11) NOT NULL AUTO_INCREMENT,
	marca					varchar(100),			
	url						varchar(255),			
	telefone				varchar(15),			
	contato					varchar(100),			
	contatoEmail			varchar(100),			
	PRIMARY KEY ( idCadProdutoMarca )
	);

CREATE TABLE cadProdutoModelos(
	idCadProdutoModelo int(11) NOT NULL AUTO_INCREMENT,
	modelo varchar(100),
	especif text,			
	PRIMARY KEY (idCadProdutoModelo)
);

CREATE TABLE cadProdutoCategorias(
	idCadProdutoCategoria int(11) NOT NULL AUTO_INCREMENT,
	categoria varchar(50),
	PRIMARY KEY (idCadProdutoCategoria)
);

CREATE TABLE cadProdutosRiscos(
	idCadProdutosRisco int(11) NOT NULL AUTO_INCREMENT,	
	descricao varchar(512),
	grau varchar(10),
	simboloVisual varchar(2048),
	PRIMARY KEY (idCadProdutosRisco)
);

INSERT INTO cadProdutosRiscos (idCadProdutosRisco, descricao, grau, simboloVisual) VALUES (1, 'Risco não Definido', 0 , '');	

CREATE TABLE cadProdutoUnidadeMedidas(
	idCadProdutoUnidadeMedida int(11) NOT NULL AUTO_INCREMENT,
	unidade varchar(50),
	sigla varchar(10),
	PRIMARY KEY (idCadProdutoUnidadeMedida)
);

CREATE TABLE cadProdutos(
	idCadProduto int(11) NOT NULL AUTO_INCREMENT,
	descr text,
	idCadProdutoFabricante int(11), 
	idCadProdutoMarca int(11), 
	idCadProdutoModelo int(11), 
	tipoMaterial int(11) COMMENT '{ 0  Perecível, 1  Não Perecível }',
	idCadProdutosRisco int(11) DEFAULT 1, 
	idCadProdutoCategoria int(11),
	imagens varchar(2048),
	idCadProdutoUnidadeMedida int(11),
	tags varchar(2048),
	ativo char(1) DEFAULT 'S',
	PRIMARY KEY (idCadProduto)
);

ALTER TABLE cadProdutos ADD CONSTRAINT fk_CadProdutoFabricante 
FOREIGN KEY 	(idCadProdutoFabricante) 
REFERENCES 	cadProdutoFabricantes (idCadProdutoFabricante);

ALTER TABLE cadProdutos ADD CONSTRAINT fk_CadProdutoMarca 
FOREIGN KEY (idCadProdutoMarca) 
REFERENCES 	cadProdutoMarcas (idCadProdutoMarca);


ALTER TABLE cadProdutos ADD CONSTRAINT fk_CadProdutoModelo 
FOREIGN KEY (idCadProdutoModelo) 
REFERENCES 	cadProdutoModelos (idCadProdutoModelo);

ALTER TABLE cadProdutos ADD CONSTRAINT fk_CadProdutosRisco 
FOREIGN KEY (idCadProdutosRisco) 
REFERENCES cadProdutosRiscos (idCadProdutosRisco);

ALTER TABLE cadProdutos ADD CONSTRAINT fk_UnidadeMedida 
FOREIGN KEY (idCadProdutoUnidadeMedida)
REFERENCES cadProdutoUnidadeMedidas (idCadProdutoUnidadeMedida);

ALTER TABLE cadProdutos ADD CONSTRAINT fk_CadProdutoCategoria 
FOREIGN KEY (idCadProdutoCategoria) 
REFERENCES  cadProdutoCategorias (idCadProdutoCategoria);

CREATE TABLE cadProdutoPrecoVenda(
	idCadProdutoPrecoVenda INT(11) NOT NULL AUTO_INCREMENT,
	idCadProduto INT(11),
	dataPreco TIMESTAMP,
	valor NUMERIC(11,2),
	PRIMARY KEY (idCadProdutoPrecoVenda)
);

CREATE TABLE ctrlCaixaMovimento(
	idCaixaMovimento INT NOT NULL AUTO_INCREMENT,
	dataHora TIMESTAMP,
	idCtrlCaixa INT, 
	tipoMovimento INT, -- { pagamento, venda, sangria, roubo, realimentação }
	operacao INT, -- { entrada, saida}
	valor NUMERIC(11,2),
	PRIMARY KEY (idCaixaMovimento)
);

CREATE TABLE ctrlCaixaVenda(
	idVenda INT NOT NULL AUTO_INCREMENT,
	idCaixaMovimento INT,
	cpfCnpjCliente VARCHAR(14),
	notaFiscal INT,
	PRIMARY KEY (idVenda)
);

CREATE TABLE ctrlCaixaVendaItem(
	idVenda INT NOT NULL,
	idProduto INT NOT NULL,
	quantidade NUMERIC(11,3) NOT NULL,
	preco NUMERIC(11,3) NOT NULL,
	PRIMARY KEY(idVenda, idProduto)
);

insert into cadProdutoFabricantes (idCadProdutoFabricante,razaoSocial) values 
	(1,'Fabricante1'),
	(2,'Fabricante2'),
	(3,'Fabricante3'),
	(4,'Fabricante4'),
	(5,'Fabricante5'),
	(6,'Fabricante6'),
	(7,'Fabricante7'),
	(8,'Fabricante8'),
	(9,'Fabricante9'),
	(10,'Fabricante10');

insert into cadProdutoMarcas (idCadProdutoMarca,marca) values
	(1,'Marca1'),
	(2,'Marca2'),
	(3,'Marca3'),
	(4,'Marca4'),
	(5,'Marca5'),
	(6,'Marca6'),
	(7,'Marca7'),
	(8,'Marca8'),
	(9,'Marca9'),
	(10,'Marca10');

insert into cadProdutoModelos (idCadProdutoModelo,modelo) values
	(1,'Modelo1'),
	(2,'Modelo2'),
	(3,'Modelo3'),
	(4,'Modelo4'),
	(5,'Modelo5'),
	(6,'Modelo6'),
	(7,'Modelo7'),
	(8,'Modelo8'),
	(9,'Modelo9'),
	(10,'Modelo10');

insert into cadProdutoCategorias (idCadProdutoCategoria,categoria) values
	(1,'Categoria1'),
	(2,'Categoria2'),
	(3,'Categoria3'),
	(4,'Categoria4'),
	(5,'Categoria5'),
	(6,'Categoria6'),
	(7,'Categoria7'),
	(8,'Categoria8'),
	(9,'Categoria9'),
	(10,'Categoria10');

insert into cadProdutos (idCadProduto,idCadProdutoFabricante,idCadProdutoMarca,idCadProdutoModelo,idCadProdutoCategoria,tipoMaterial) values
	(1,1,1,1,1,1),
	(2,2,2,2,2,1),
	(3,3,3,3,3,1),
	(4,4,4,4,4,1),
	(5,5,5,5,5,1),
	(6,6,6,6,6,1),
	(7,7,7,7,7,1),
	(8,8,8,8,8,1),
	(9,9,9,9,9,1),
	(10,10,10,10,10,1);

insert into cadProdutos (idCadProduto,idCadProdutoFabricante,idCadProdutoMarca,idCadProdutoModelo,idCadProdutoCategoria,tipoMaterial,ativo) values
	(11,1,1,1,1,1,'N'),
	(12,2,2,2,2,1,'N'),
	(13,3,3,3,3,1,'N');
	
insert into cadProdutoPrecoVenda (idCadProdutoPrecoVenda,idCadProduto,dataPreco,valor) values
	(1,1,NOW(),10),
	(2,2,NOW(),10),
	(3,3,NOW(),10),
	(4,4,NOW(),10),
	(5,5,NOW(),10),
	(6,6,NOW(),10),
	(7,7,NOW(),10),
	(8,8,NOW(),10),
	(9,9,NOW(),10),
	(10,10,NOW(),10);