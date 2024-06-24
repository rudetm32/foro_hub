CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT,
    autor VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    curso_id BIGINT,
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- Índice opcional para buscar por título
CREATE INDEX idx_titulo ON topicos(titulo);
