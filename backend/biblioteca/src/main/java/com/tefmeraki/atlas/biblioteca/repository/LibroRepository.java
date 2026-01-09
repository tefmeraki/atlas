package com.tefmeraki.atlas.biblioteca.repository;

import com.tefmeraki.atlas.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
