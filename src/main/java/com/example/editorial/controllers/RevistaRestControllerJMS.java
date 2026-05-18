package com.example.editorial.controllers;

import com.example.editorial.models.RevistaJMS;
import com.example.editorial.services.RevistaServiceJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revistas")
public class RevistaRestControllerJMS {

    @Autowired
    private RevistaServiceJMS revistaService;

    // GET /api/revistas  (con filtro opcional por nombre)
    @GetMapping
    public List<RevistaJMS> listar(@RequestParam(required = false) String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return revistaService.findByNombre(nombre);
        }
        return revistaService.findAll();
    }

    // GET /api/revistas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RevistaJMS> obtener(@PathVariable Long id) {
        return revistaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/revistas
    @PostMapping
    public RevistaJMS crear(@RequestBody RevistaJMS revista) {
        return revistaService.save(revista);
    }

    // PUT /api/revistas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RevistaJMS> actualizar(@PathVariable Long id, @RequestBody RevistaJMS revista) {
        return revistaService.findById(id).map(r -> {
            revista.setId(id);
            return ResponseEntity.ok(revistaService.save(revista));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/revistas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        return revistaService.findById(id).map(r -> {
            revistaService.deleteById(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}