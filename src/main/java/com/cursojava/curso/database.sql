CREATE DATABASE cursojava CHARACTER SET utf8 COLLATE 'utf8_bin';

CREATE TABLE usuarios(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(40) NOT NULL,
    telefono INT(20) NOT NULL,
    email VARCHAR(80) NOT NULL,
    password VARCHAR(50) NOT NULL
     );

ALTER TABLE usuarios CHANGE telefono telefono BIGINT NOT NULL;

USE cursojava;



INSERT INTO usuarios (nombre,apellidos,telefono,email,password) VALUES ("Samanta","Medina Garduno",7228762266,"sam@gmail.com",'passwordSecreto123');
INSERT INTO usuarios (nombre,apellidos,telefono,email,password) VALUES ("Lucas","Moy Bell",4567891235,"Moy@hotmail.com",'passwordSuperSecreto');

