package com.inventario.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materiales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {


    @Id //no es necesario el column  el  jpa asume que es la columna es la de id con el @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "stock_minimo", nullable = false)
    private int stockMinimo;

    @Column(name = "stock_maximo", nullable = false)
    private int stockMaximo;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    @JsonBackReference
    private Proveedor proveedor;
}
