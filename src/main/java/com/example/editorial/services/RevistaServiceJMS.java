package com.example.editorial.services;

import com.example.editorial.models.RevistaJMS;
import com.example.editorial.repositories.RevistaRepositoryJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevistaServiceJMS {

    @Autowired
    private RevistaRepositoryJMS revistaRepository;

    // Listar todas
    public List<RevistaJMS> findAll() {
        return revistaRepository.findAll();
    }

    // Buscar por ID
    public Optional<RevistaJMS> findById(Long id) {
        return revistaRepository.findById(id);
    }

    // Crear o actualizar
    public RevistaJMS save(RevistaJMS revista) {
        return revistaRepository.save(revista);
    }

    // Borrar
    public void deleteById(Long id) {
        revistaRepository.deleteById(id);
    }

    // Buscar por nombre
    public List<RevistaJMS> findByNombre(String nombre) {
        return revistaRepository.findByNombreContainingIgnoreCase(nombre);
    }
}