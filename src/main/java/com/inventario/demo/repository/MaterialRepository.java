package com.inventario.demo.repository;

import com.inventario.demo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByNombreContainingIgnoreCase(String nombre);
}
