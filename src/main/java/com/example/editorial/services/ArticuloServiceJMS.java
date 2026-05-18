package com.example.editorial.services;

import com.example.editorial.models.ArticuloJMS;
import com.example.editorial.repositories.ArticuloRepositoryJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceJMS {

    @Autowired
    private ArticuloRepositoryJMS articuloRepository;

    // Listar todos
    public List<ArticuloJMS> findAll() {
        return articuloRepository.findAll();
    }

    // Buscar por ID
    public Optional<ArticuloJMS> findById(Long id) {
        return articuloRepository.findById(id);
    }

    // Crear o actualizar
    public ArticuloJMS save(ArticuloJMS articulo) {
        return articuloRepository.save(articulo);
    }

    // Borrar
    public void deleteById(Long id) {
        articuloRepository.deleteById(id);
    }

    // Filtrar por openAccess
    public List<ArticuloJMS> findByOpenAccess(Boolean openAccess) {
        return articuloRepository.findByOpenAccess(openAccess);
    }

    // Buscar por autor
    public List<ArticuloJMS> findByAutor(String autor) {
        return articuloRepository.findByAutorContainingIgnoreCase(autor);
    }

    // Artículos de una revista
    public List<ArticuloJMS> findByRevista(Long revistaId) {
        return articuloRepository.findByRevistaId(revistaId);
    }

    // Artículos publicados en un año
    public List<ArticuloJMS> findByAnio(int anio) {
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        return articuloRepository.findByFechaPublicacionBetween(inicio, fin);
    }
}