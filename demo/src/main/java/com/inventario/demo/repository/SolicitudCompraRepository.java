package com.inventario.demo.repository;

import com.inventario.demo.model.SolicitudCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudCompraRepository extends JpaRepository<SolicitudCompra, Long> {
}
