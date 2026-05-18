package com.example.editorial.controllers;

import com.example.editorial.models.ArticuloJMS;
import com.example.editorial.services.ArticuloServiceJMS;
import com.example.editorial.services.RevistaServiceJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/articulos")
public class ArticuloWebControllerJMS {

    @Autowired
    private ArticuloServiceJMS articuloService;

    @Autowired
    private RevistaServiceJMS revistaService;

    // GET /web/articulos
    @GetMapping
    public String listar(@RequestParam(required = false) String autor,
                         @RequestParam(required = false) Boolean openAccess,
                         Model model) {
        if (autor != null && !autor.isEmpty()) {
            model.addAttribute("articulos", articuloService.findByAutor(autor));
        } else if (openAccess != null) {
            model.addAttribute("articulos", articuloService.findByOpenAccess(openAccess));
        } else {
            model.addAttribute("articulos", articuloService.findAll());
        }
        model.addAttribute("autor", autor);
        model.addAttribute("openAccess", openAccess);
        return "articulos/lista";
    }

    // GET /web/articulos/{id}
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return articuloService.findById(id).map(a -> {
            model.addAttribute("articulo", a);
            return "articulos/detalle";
        }).orElse("redirect:/web/articulos");
    }

    // GET /web/articulos/nuevo
    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("articulo", new ArticuloJMS());
        model.addAttribute("revistas", revistaService.findAll());
        return "articulos/formulario";
    }

    // POST /web/articulos/nuevo
    @PostMapping("/nuevo")
    public String guardar(@ModelAttribute ArticuloJMS articulo) {
        articuloService.save(articulo);
        return "redirect:/web/articulos";
    }

    // GET /web/articulos/editar/{id}
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        return articuloService.findById(id).map(a -> {
            model.addAttribute("articulo", a);
            model.addAttribute("revistas", revistaService.findAll());
            return "articulos/formulario";
        }).orElse("redirect:/web/articulos");
    }

    // POST /web/articulos/editar/{id}
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute ArticuloJMS articulo) {
        articulo.setId(id);
        articuloService.save(articulo);
        return "redirect:/web/articulos";
    }

    // POST /web/articulos/borrar/{id}
    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        articuloService.deleteById(id);
        return "redirect:/web/articulos";
    }
}
