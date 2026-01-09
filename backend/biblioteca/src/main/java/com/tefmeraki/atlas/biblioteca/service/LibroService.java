package com.tefmeraki.atlas.biblioteca.service;

import com.tefmeraki.atlas.biblioteca.entity.Libro;
import com.tefmeraki.atlas.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Transactional(readOnly = true)
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro obtener(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Libro no encontrado"));
    }

    public Libro crear(Libro libro) {
        // MVP: sin reglas complejas
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro cambios) {
        Libro existente = obtener(id);
        existente.setTitulo(cambios.getTitulo());
        existente.setAutor(cambios.getAutor());
        existente.setIsbn(cambios.getIsbn());
        existente.setAnioPublicacion(cambios.getAnioPublicacion());
        return libroRepository.save(existente);
    }

    public void borrar(Long id) {
        Libro existente = obtener(id);
        libroRepository.delete(existente);
    }
}
