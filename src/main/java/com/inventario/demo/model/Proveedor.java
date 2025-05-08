package com.inventario.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String contacto;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Material> materiales;
}
