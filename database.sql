# Primeira versão do banco 0.1
CREATE DATABASE cashcontrol;

CREATE TABLE IF NOT EXISTS category
(
    id_category int                         NOT NULL AUTO_INCREMENT,
    name        varchar(100)                NOT NULL,
    description varchar(240),
    type        enum ('R', 'D') DEFAULT 'D' NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id_category)
);

CREATE TABLE IF NOT EXISTS subcategory
(
    id_subcategory int          NOT NULL AUTO_INCREMENT,
    id_category    int,
    name           varchar(100) NOT NULL,
    description    varchar(240),
    CONSTRAINT subcategory_pk PRIMARY KEY (id_subcategory),
    CONSTRAINT category_subcategory_fk
        FOREIGN KEY (id_category) REFERENCES category (id_category)
);

CREATE TABLE movimentation
(
    id_movimentation int                     NOT NULL,
    value            decimal(19, 2) unsigned NOT NULL,
    date             date                    NOT NULL,
    id_subcategory   int                     NOT NULL,
    CONSTRAINT movimentation_pk PRIMARY KEY (id_movimentation),
    CONSTRAINT movimentation_subcategory_fk
        FOREIGN KEY (id_subcategory) REFERENCES subcategory (id_subcategory)
);