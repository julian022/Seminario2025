package com.inventario.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private String estado; // Ej.: "PENDIENTE", "APROBADA", "RECHAZADA"

    private LocalDateTime fechaSolicitud = LocalDateTime.now();
}
