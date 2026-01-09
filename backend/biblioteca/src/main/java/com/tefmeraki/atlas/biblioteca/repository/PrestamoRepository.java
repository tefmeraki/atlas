package com.tefmeraki.atlas.biblioteca.repository;

import com.tefmeraki.atlas.biblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query("select count(p) > 0 from Prestamo p where p.libro.id = :libroId and p.fechaDevolucion is null")
    boolean existsPrestamoActivoParaLibro(@Param("libroId") Long libroId);
}
