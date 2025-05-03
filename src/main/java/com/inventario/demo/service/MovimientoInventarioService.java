package com.inventario.demo.service;

import com.inventario.demo.model.MovimientoInventario;
import com.inventario.demo.repository.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public List<MovimientoInventario> findAll() {
        return movimientoRepository.findAll();
    }

    public MovimientoInventario save(MovimientoInventario movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public MovimientoInventario findById(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }
}