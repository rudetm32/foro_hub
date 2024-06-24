package com.forohub.forumapp.domain.curso;

public record DatosCurso(Long id, String titulo) {

    public DatosCurso(String titulo) {
        this(null, titulo); // Llama al constructor principal con id null
    }


}
