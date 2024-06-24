package com.forohub.forumapp.domain.topico;

import com.forohub.forumapp.domain.curso.Curso;

public record DatosListadoTopico(
         Long id,
         String titulo,
         String mensaje,
         String autor,
         Status status,
         String tituloCurso
) {
    public DatosListadoTopico(Topico topico, Curso curso){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(),topico.getStatus(), curso.getTitulo());
    }

}
