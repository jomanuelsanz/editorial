package com.example.editorial.controllers;

import com.example.editorial.models.ArticuloJMS;
import com.example.editorial.services.ArticuloServiceJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloRestControllerJMS {

    @Autowired
    private ArticuloServiceJMS articuloService;

    // GET /api/articulos  (filtros opcionales)
    @GetMapping
    public List<ArticuloJMS> listar(
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) Boolean openAccess,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Long revistaId) {

        if (autor != null && !autor.isEmpty()) return articuloService.findByAutor(autor);
        if (openAccess != null) return articuloService.findByOpenAccess(openAccess);
        if (anio != null) return articuloService.findByAnio(anio);
        if (revistaId != null) return articuloService.findByRevista(revistaId);
        return articuloService.findAll();
    }

    // GET /api/articulos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ArticuloJMS> obtener(@PathVariable Long id) {
        return articuloService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/articulos
    @PostMapping
    public ArticuloJMS crear(@RequestBody ArticuloJMS articulo) {
        return articuloService.save(articulo);
    }

    // PUT /api/articulos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ArticuloJMS> actualizar(@PathVariable Long id, @RequestBody ArticuloJMS articulo) {
        return articuloService.findById(id).map(a -> {
            articulo.setId(id);
            return ResponseEntity.ok(articuloService.save(articulo));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/articulos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        return articuloService.findById(id).map(a -> {
            articuloService.deleteById(id);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}