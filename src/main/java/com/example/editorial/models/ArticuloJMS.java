package com.example.editorial.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "articulos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticuloJMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private Integer numeroPaginas;
    private LocalDate fechaPublicacion;
    private Boolean openAccess;

    @ManyToOne
    @JoinColumn(name = "revista_id")
    private RevistaJMS revista;
}
