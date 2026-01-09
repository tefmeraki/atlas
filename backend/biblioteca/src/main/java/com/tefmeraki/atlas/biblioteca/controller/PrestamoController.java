package com.tefmeraki.atlas.biblioteca.controller;

import com.tefmeraki.atlas.biblioteca.entity.Prestamo;
import com.tefmeraki.atlas.biblioteca.service.PrestamoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listar();
    }

    @PostMapping
    public Prestamo crear(@Valid @RequestBody CrearPrestamoRequest req) {
        return prestamoService.crearPrestamo(req.libroId(), req.socioId());
    }

    @PutMapping("/{id}/devolver")
    public Prestamo devolver(@PathVariable Long id) {
        return prestamoService.devolver(id);
    }

    public record CrearPrestamoRequest(@NotNull Long libroId, @NotNull Long socioId) {}
}
