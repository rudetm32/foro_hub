package com.forohub.forumapp.controller;

import com.forohub.forumapp.domain.topico.*;
import com.forohub.forumapp.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService, TopicoRepository topicoRepository) {
        this.topicoService = topicoService;
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        try {
            var topico = topicoService.registrarTopico(datosRegistroTopico);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();

            return ResponseEntity.created(url).body(topico);

        } catch (ValidacionDeIntegridad ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listaTopicos(@PageableDefault(size = 10, sort = "autor") Pageable paginacion) {
        Page<Topico> topicos = topicoService.listarTopicosOrdenados(paginacion);
        Page<DatosListadoTopico> datosListadoTopicos = topicos.map(topico -> new DatosListadoTopico(topico, topico.getCurso()));

        return ResponseEntity.ok(datosListadoTopicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> encontrarTopicoPorId(@PathVariable Long id) {
        DatosRespuestaTopico datosRespuestaTopico = topicoService.encontrarTopicoPorId(id);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        try {
            topicoService.eliminarTopico(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado con ID: " + id);
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizarDatos(@RequestBody  @Valid DatosActualizarTopico datosActualizarTopico){
        try {
            topicoService.actualizarDatosTopico(datosActualizarTopico);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado con ID: " +  datosActualizarTopico.id());
        }
    }
}


