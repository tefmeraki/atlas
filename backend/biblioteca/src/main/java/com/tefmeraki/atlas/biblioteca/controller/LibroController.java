package com.tefmeraki.atlas.biblioteca.controller;

import com.tefmeraki.atlas.biblioteca.entity.Libro;
import com.tefmeraki.atlas.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listar() {
        return libroService.listar();
    }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable Long id) {
        return libroService.obtener(id);
    }

    @PostMapping
    public Libro crear(@Valid @RequestBody Libro libro) {
        return libroService.crear(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @Valid @RequestBody Libro cambios) {
        return libroService.actualizar(id, cambios);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        libroService.borrar(id);
    }
}
