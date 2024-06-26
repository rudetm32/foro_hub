CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL
);

INSERT INTO cursos (titulo) VALUES
    ('Fundamentos de Java y Programación Orientada a Objetos'),
    ('Desarrollo de Aplicaciones con Spring Boot'),
    ('Introducción a SQL y Gestión de Bases de Datos'),
    ('Consultas Avanzadas y Optimización en SQL');
