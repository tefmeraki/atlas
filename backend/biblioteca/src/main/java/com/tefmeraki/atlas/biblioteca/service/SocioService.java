package com.tefmeraki.atlas.biblioteca.service;

import com.tefmeraki.atlas.biblioteca.entity.Socio;
import com.tefmeraki.atlas.biblioteca.repository.SocioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class SocioService {

    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;
    }

    @Transactional(readOnly = true)
    public List<Socio> listar() {
        return socioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Socio obtener(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Socio no encontrado"));
    }

    public Socio crear(Socio socio) {
        return socioRepository.save(socio);
    }

    public Socio actualizar(Long id, Socio cambios) {
        Socio existente = obtener(id);
        existente.setNombre(cambios.getNombre());
        existente.setApellidos(cambios.getApellidos());
        existente.setEmail(cambios.getEmail());
        return socioRepository.save(existente);
    }

    public void borrar(Long id) {
        Socio existente = obtener(id);
        socioRepository.delete(existente);
    }
}
