package com.example.editorial.controllers;

import com.example.editorial.models.RevistaJMS;
import com.example.editorial.services.RevistaServiceJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/revistas")
public class RevistaWebControllerJMS {

    @Autowired
    private RevistaServiceJMS revistaService;

    // GET /web/revistas
    @GetMapping
    public String listar(@RequestParam(required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("revistas", revistaService.findByNombre(nombre));
        } else {
            model.addAttribute("revistas", revistaService.findAll());
        }
        model.addAttribute("nombre", nombre);
        return "revistas/lista";
    }

    // GET /web/revistas/{id}
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return revistaService.findById(id).map(r -> {
            model.addAttribute("revista", r);
            return "revistas/detalle";
        }).orElse("redirect:/web/revistas");
    }

    // GET /web/revistas/nueva
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("revista", new RevistaJMS());
        return "revistas/formulario";
    }

    // POST /web/revistas/nueva
    @PostMapping("/nueva")
    public String guardar(@ModelAttribute RevistaJMS revista) {
        revistaService.save(revista);
        return "redirect:/web/revistas";
    }

    // GET /web/revistas/editar/{id}
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        return revistaService.findById(id).map(r -> {
            model.addAttribute("revista", r);
            return "revistas/formulario";
        }).orElse("redirect:/web/revistas");
    }

    // POST /web/revistas/editar/{id}
    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute RevistaJMS revista) {
        revista.setId(id);
        revistaService.save(revista);
        return "redirect:/web/revistas";
    }

    // POST /web/revistas/borrar/{id}
    @PostMapping("/borrar/{id}")
    public String borrar(@PathVariable Long id) {
        revistaService.deleteById(id);
        return "redirect:/web/revistas";
    }
}