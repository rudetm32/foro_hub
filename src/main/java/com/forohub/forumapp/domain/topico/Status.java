package com.forohub.forumapp.domain.topico;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.forohub.forumapp.infra.errores.ValidacionDeIntegridad;

public enum Status {
    ACTIVO("ACTIVO"),
    CERRADO("CERRADO"),
    RESUELTO("RESUELTO");

    private final String alias;

    Status(String alias) {
        this.alias = alias;
    }

    @JsonValue
    public String getAlias() {
        return alias;
    }

    @JsonCreator
    public static Status fromAlias(String alias) {
        for (Status status : Status.values()) {
            if (status.alias.equalsIgnoreCase(alias)) {
                return status;
            }
        }
        throw new ValidacionDeIntegridad("Status no válido: " + alias + ". Seleccione un status válido entre: ABIERTO, CERRADO, RESUELTO");
    }
}
