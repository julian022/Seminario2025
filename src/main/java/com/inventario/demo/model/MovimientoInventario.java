package com.inventario.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private String tipoMovimiento; // "ENTRADA" o "SALIDA"

    private LocalDateTime fechaMovimiento = LocalDateTime.now();
}
