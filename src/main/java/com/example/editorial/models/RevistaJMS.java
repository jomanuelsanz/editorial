package com.example.editorial.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "revistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RevistaJMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tematica;
    private String periodicidad; // mensual, trimestral, anual...
    private String issn;

    @OneToMany(mappedBy = "revista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloJMS> articulos;
}