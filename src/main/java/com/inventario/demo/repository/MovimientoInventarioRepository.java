package com.inventario.demo.repository;


import com.inventario.demo.model.MovimientoInventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    List<MovimientoInventario> findByTipoMovimiento(String tipoMovimiento);
}

