package com.forohub.forumapp.domain.topico.validaciones;

import com.forohub.forumapp.domain.curso.CursoRepository;
import com.forohub.forumapp.domain.topico.DatosRegistroTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarIdCurso implements ValidadorDeTopicos {

    private final CursoRepository cursoRepository;

    @Autowired
    public ValidarIdCurso(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void validar(DatosRegistroTopico datos) {
        if (datos.cursoId() == null) {
            throw new ValidationException("El ID del curso no debe ser nulo.");
        }
        var validarId = cursoRepository.findById(datos.cursoId());
        if (validarId.isEmpty()) {
            throw new ValidationException("Curso no encontrado con ID: " + datos.cursoId());
        }
    }
}
