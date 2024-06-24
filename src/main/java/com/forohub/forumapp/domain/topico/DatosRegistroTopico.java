package com.forohub.forumapp.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull(message = "El campo titulo no puede estar vacio")
        String titulo,
        @NotNull(message = "El campo autor no puede estar vacio")
        String autor,
        @NotNull(message = "El campo mensaje no puede estar vacio")
        String mensaje,
        @NotNull
        Status status,
        @NotNull
        Long cursoId
) {
}
