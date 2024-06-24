CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL
);

INSERT INTO cursos (titulo) VALUES
    ('Curso de Programación'),
    ('Curso de Matemáticas'),
    ('Curso de Historia');
