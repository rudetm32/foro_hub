package com.forohub.forumapp.domain.topico.validaciones;

import com.forohub.forumapp.domain.topico.DatosRegistroTopico;
import com.forohub.forumapp.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUnicidadTituloMensaje implements ValidadorDeTopicos{

    private final TopicoRepository topicoRepository;

    @Autowired
    public ValidadorUnicidadTituloMensaje(TopicoRepository topicoRepository){
        this.topicoRepository=topicoRepository;
    }
    @Override
    public void validar(DatosRegistroTopico datos){

        var tituloMensajeDuplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        var tituloDuplicado = topicoRepository.existsByTitulo(datos.titulo());
        var mensajeDuplicado = topicoRepository.existsByMensaje(datos.mensaje());

        if (tituloMensajeDuplicado){
            throw new ValidationException("Ya existe un tópico con el mismo título y contenido: " + datos.titulo() + " " + datos.mensaje());
        }
        if (tituloDuplicado) {
            throw new ValidationException(("Ya existe un tópico con el mismo titulo: " + datos.titulo()));
        }
        if (mensajeDuplicado) {
            throw new ValidationException(("Ya existe un tópico con el mismo mensaje: " + datos.mensaje()));
        }
    }
}
