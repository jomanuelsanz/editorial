package com.example.editorial.repositories;

import com.example.editorial.models.ArticuloJMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ArticuloRepositoryJMS extends JpaRepository<ArticuloJMS, Long> {

    // Filtrar por acceso abierto
    List<ArticuloJMS> findByOpenAccess(Boolean openAccess);

    // Buscar por autor
    List<ArticuloJMS> findByAutorContainingIgnoreCase(String autor);

    // Artículos de una revista
    List<ArticuloJMS> findByRevistaId(Long revistaId);

    // Artículos publicados en un año (entre fechas)
    List<ArticuloJMS> findByFechaPublicacionBetween(LocalDate inicio, LocalDate fin);

    // Contar artículos de una revista en un año
@Query("SELECT COUNT(a) FROM ArticuloJMS a WHERE a.revista.id = :revistaId AND YEAR(a.fechaPublicacion) = :anio")
Long contarPorRevistaYAnio(@Param("revistaId") Long revistaId, @Param("anio") int anio);

}
