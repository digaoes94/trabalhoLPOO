DROP TABLE visitantes;
DROP TABLE associados;
DROP TABLE funcionarios;
DROP TABLE livros;
DROP TABLE revistas;
DROP TABLE periodicos;
DROP TABLE dvds;
DROP TABLE pdfs;
DROP TABLE emprestimos;

CREATE TABLE visitantes(
	ID int,
    Nome varchar(255),
    CPF varchar(255), 
	Email varchar(255),
    Celular varchar(13),
    Dívida float(2)
);
CREATE TABLE associados(
	ID int,
    Nome varchar(255),
    CPF varchar(255), 
	Email varchar(255),
    Celular varchar(13),
    Dívida float(2),
    Matricula varchar(255),
    Senha varchar(255)
);
CREATE TABLE funcionarios(
	ID int,
    Nome varchar(255),
    CPF varchar(255), 
	Email varchar(255),
    Celular varchar(13),
    Matricula varchar(255)
);
CREATE TABLE livros(
	ID int,
    Nome varchar(255),
    Genero varchar(255),
    Descrição varchar(255),
    Localização varchar(255),
    Autor varchar(255)
);
CREATE TABLE revistas(
	ID int,
    Nome varchar(255),
    Genero varchar(255),
    Descrição varchar(255),
    Localização varchar(255),
    Fotógrafo varchar(255),
    Edição varchar(255)
);
CREATE TABLE periodicos(
	ID int,
    Nome varchar(255),
    Genero varchar(255),
    Descrição varchar(255),
    Localização varchar(255),
    Área varchar(255),
    Subárea varchar(255)
);
CREATE TABLE dvds(
	ID int,
    Nome varchar(255),
    Genero varchar(255),
    Descrição varchar(255),
    Localização varchar(255),
    Executável bool
);
CREATE TABLE pdfs(
	ID int,
    Nome varchar(255),
    Genero varchar(255),
    Descrição varchar(255),
    Localização varchar(255),
    Executável bool
);
CREATE TABLE emprestimos_livro(

);
CREATE TABLE emprestimos_revista(

);
CREATE TABLE emprestimos_periodico(

);