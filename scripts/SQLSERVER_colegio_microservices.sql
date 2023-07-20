CREATE DATABASE ColegioMicroserviceIRypS;

USE [ColegioMicroserviceIRypS];


CREATE TABLE curso (
    id_curso INT PRIMARY KEY IDENTITY(1,1),
    descripcion VARCHAR(50) NOT NULL,
    estado TINYINT NOT NULL
);

INSERT INTO curso (descripcion, estado)
VALUES ('Análisis y Diseño', 1),
       ('Base de Datos', 1),
       ('Lenguaje de Programación', 1),
       ('Inteligencia Artificial', 1);