# Primeira versão do banco 0.1
CREATE DATABASE cashcontrol;

CREATE TABLE IF NOT EXISTS categorias
(
    id_categoria int                         NOT NULL AUTO_INCREMENT,
    nome         varchar(100)                NOT NULL,
    descricao    varchar(240),
    tipo         enum ('R', 'D') DEFAULT 'D' NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY (id_categoria)
);


CREATE TABLE IF NOT EXISTS subcategorias
(
    id_subcategoria int          NOT NULL AUTO_INCREMENT,
    id_categoria    int,
    nome            varchar(100) NOT NULL,
    descricao       varchar(240),
    CONSTRAINT subcategoria_pk PRIMARY KEY (id_subcategoria),
    CONSTRAINT categoria_subcategoria_fk
        FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
);


CREATE TABLE movimentacoes
(
    id_movimentacao int                     NOT NULL,
    valor           decimal(19, 2) unsigned NOT NULL,
    DATA            date                    NOT NULL,
    id_subcategoria int                     NOT NULL,
    CONSTRAINT movimentacoes_pk PRIMARY KEY (id_movimentacao),
    CONSTRAINT movimentacao_subcategoria_fk
        FOREIGN KEY (id_subcategoria) REFERENCES subcategorias (id_subcategoria)
);

