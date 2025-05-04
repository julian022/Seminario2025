package com.inventario.demo.service;

import com.inventario.demo.model.Material;
import com.inventario.demo.model.MovimientoInventario;
import com.inventario.demo.repository.MovimientoInventarioRepository;
import com.inventario.demo.repository.MaterialRepository;
import com.inventario.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final MaterialRepository materialRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository,
                                       MaterialRepository materialRepository) {
        this.movimientoRepository = movimientoRepository;
        this.materialRepository = materialRepository;
    }

    public List<MovimientoInventario> findAll() {
        return movimientoRepository.findAll();
    }

    public MovimientoInventario findById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Transactional
    public MovimientoInventario save(MovimientoInventario movimiento) {
        // Validar que el material exista
        Material material = materialRepository.findById(movimiento.getMaterial().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con ID: " + movimiento.getMaterial().getId()));

        int cantidad = movimiento.getCantidad();

        if ("ENTRADA".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            material.setCantidad(material.getCantidad() + cantidad);
        } else if ("SALIDA".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
            if (material.getCantidad() < cantidad) {
                throw new IllegalArgumentException("No hay suficiente stock para la salida.");
            }
            material.setCantidad(material.getCantidad() - cantidad);
        } else {
            throw new IllegalArgumentException("Tipo de movimiento no válido. Debe ser 'ENTRADA' o 'SALIDA'.");
        }

        // Guardar cambios en el material
        materialRepository.save(material);

        // Guardar el movimiento
        return movimientoRepository.save(movimiento);
    }
}
