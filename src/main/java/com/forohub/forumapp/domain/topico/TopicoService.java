package com.forohub.forumapp.domain.topico;

import com.forohub.forumapp.domain.curso.Curso;
import com.forohub.forumapp.domain.curso.CursoRepository;
import com.forohub.forumapp.domain.curso.DatosCurso;
import com.forohub.forumapp.domain.topico.validaciones.ValidadorDeTopicos;
import com.forohub.forumapp.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicoService {

    private final List<ValidadorDeTopicos> validadores;
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public TopicoService(List<ValidadorDeTopicos> validadores, TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.validadores = validadores;
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        // Realizar todas las validaciones
        validadores.forEach(v -> v.validar(datosRegistroTopico));

        Curso curso = obtenerCurso(datosRegistroTopico);

        // Crear y guardar el tópico
        Topico topico = new Topico(datosRegistroTopico, curso);
        topico = topicoRepository.save(topico);

        return convertirDatos(topico);
    }

    private Curso obtenerCurso(DatosRegistroTopico datos) {
        return cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Curso no encontrado con ID: " + datos.cursoId()));
    }

    private DatosRespuestaTopico convertirDatos(Topico topico) {
        Curso curso = topico.getCurso();
        DatosCurso datosCurso = new DatosCurso(curso.getId(), curso.getTitulo());
        return new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getAutor(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getFecha_creacion() != null ? topico.getFecha_creacion().toString() : null,
                curso.getId(),
                datosCurso// Pasar CursoDTO
        );
    }

    public Page<Topico> listarTopicosOrdenados(Pageable paginacion) {
        return topicoRepository.findAll(paginacion);
    }

    public DatosRespuestaTopico encontrarTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("Tópico no encontrado con ID: " + id));
        return convertirDatos(topico);
    }

    public void eliminarTopico(Long id) {
    Optional<Topico> optionalTopico = topicoRepository.findById(id);
        optionalTopico.ifPresentOrElse(
                topico -> topicoRepository.delete(topico),
                () -> {
                    throw new EntityNotFoundException("Tópico no encontrado con ID: " + id);
                }
        );
    }

    public void actualizarDatosTopico(DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> topicoActualizado = topicoRepository.findById(datosActualizarTopico.id());
            topicoActualizado.ifPresentOrElse(
                    topico-> topico.actualizarTopico(datosActualizarTopico),
                    () -> {
                        throw new EntityNotFoundException("Tópico mo encontrado con ID: " + datosActualizarTopico.id());
                    }
            );
    }
}
