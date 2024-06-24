package com.forohub.forumapp.domain.topico;

import com.forohub.forumapp.domain.curso.DatosCurso;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String autor,
        String mensaje,
        Status status,
        String fecha_creacion,
        Long cursoId,
        DatosCurso curso // Incluir el objeto Curso
) {
}
