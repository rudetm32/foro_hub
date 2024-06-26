-- db/migration/V1__add_status_column_to_topico.sql

-- Añadir columna 'status' a la tabla 'topicos'
ALTER TABLE topicos
ADD COLUMN  status ENUM('ACTIVO', 'CERRADO', 'RESUELTO') NOT NULL DEFAULT 'ACTIVO';
