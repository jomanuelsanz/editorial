package com.example.editorial.repositories;

import com.example.editorial.models.RevistaJMS;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RevistaRepositoryJMS extends JpaRepository<RevistaJMS, Long> {
    List<RevistaJMS> findByNombreContainingIgnoreCase(String nombre);
}