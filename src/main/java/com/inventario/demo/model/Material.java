package com.inventario.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;

    @Column(name = "stock_maximo")
    private Integer stockMaximo;

    @Column(length = 500)
    private String descripcion;

    // Nuevos campos para manejo de alertas
    @Transient
    private boolean necesitaReposicion;
    
    @Transient
    private NivelAlerta nivelAlerta;

    // Enumerado para niveles de alerta
    public enum NivelAlerta {
        NORMAL,
        BAJO,
        CRITICO,
        AGOTADO
    }

    // Constructores
    public Material() {
    }

    public Material(Long id, String nombre, Integer stockActual, Integer stockMinimo, Integer stockMaximo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.descripcion = descripcion;
        actualizarEstadoAlerta();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
        actualizarEstadoAlerta();
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
        actualizarEstadoAlerta();
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos para manejo de alertas
    public boolean isNecesitaReposicion() {
        return necesitaReposicion;
    }

    public NivelAlerta getNivelAlerta() {
        return nivelAlerta;
    }

    /**
     * Calcula si el material necesita reposición y determina el nivel de alerta
     */
    private void actualizarEstadoAlerta() {
        this.necesitaReposicion = stockActual < stockMinimo;
        
        if (stockActual <= 0) {
            this.nivelAlerta = NivelAlerta.AGOTADO;
        } else if (stockActual < (stockMinimo * 0.3)) { // Menos del 30% del mínimo
            this.nivelAlerta = NivelAlerta.CRITICO;
        } else if (stockActual < stockMinimo) {
            this.nivelAlerta = NivelAlerta.BAJO;
        } else {
            this.nivelAlerta = NivelAlerta.NORMAL;
        }
    }

    /**
     * Método para verificar si el stock está en nivel crítico
     */
    public boolean isStockCritico() {
        return nivelAlerta == NivelAlerta.CRITICO || nivelAlerta == NivelAlerta.AGOTADO;
    }

    /**
     * Método para verificar si el stock está agotado
     */
    public boolean isAgotado() {
        return nivelAlerta == NivelAlerta.AGOTADO;
    }

    // Métodos utilitarios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", stockActual=" + stockActual +
                ", stockMinimo=" + stockMinimo +
                ", nivelAlerta=" + nivelAlerta +
                '}';
    }
}