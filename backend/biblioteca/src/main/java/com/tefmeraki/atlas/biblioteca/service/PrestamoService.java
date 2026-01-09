package com.tefmeraki.atlas.biblioteca.service;

import com.tefmeraki.atlas.biblioteca.entity.Libro;
import com.tefmeraki.atlas.biblioteca.entity.Prestamo;
import com.tefmeraki.atlas.biblioteca.entity.Socio;
import com.tefmeraki.atlas.biblioteca.repository.LibroRepository;
import com.tefmeraki.atlas.biblioteca.repository.PrestamoRepository;
import com.tefmeraki.atlas.biblioteca.repository.SocioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final SocioRepository socioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository, SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.socioRepository = socioRepository;
    }

    @Transactional(readOnly = true)
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    public Prestamo crearPrestamo(Long libroId, Long socioId) {
        if (prestamoRepository.existsPrestamoActivoParaLibro(libroId)) {
            throw new ResponseStatusException(BAD_REQUEST, "El libro ya tiene un préstamo activo");
        }

        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Libro no encontrado"));
        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Socio no encontrado"));

        Prestamo prestamo = new Prestamo(libro, socio, LocalDate.now());
        return prestamoRepository.save(prestamo);
    }

    public Prestamo devolver(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Préstamo no encontrado"));

        if (prestamo.getFechaDevolucion() != null) {
            throw new ResponseStatusException(BAD_REQUEST, "El préstamo ya está devuelto");
        }

        prestamo.setFechaDevolucion(LocalDate.now());
        return prestamoRepository.save(prestamo);
    }
}
