package com.tefmeraki.atlas.biblioteca.controller;

import com.tefmeraki.atlas.biblioteca.entity.Socio;
import com.tefmeraki.atlas.biblioteca.service.SocioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public List<Socio> listar() {
        return socioService.listar();
    }

    @GetMapping("/{id}")
    public Socio obtener(@PathVariable Long id) {
        return socioService.obtener(id);
    }

    @PostMapping
    public Socio crear(@Valid @RequestBody Socio socio) {
        return socioService.crear(socio);
    }

    @PutMapping("/{id}")
    public Socio actualizar(@PathVariable Long id, @Valid @RequestBody Socio cambios) {
        return socioService.actualizar(id, cambios);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        socioService.borrar(id);
    }
}
