package com.inventario.demo.services;

import com.inventario.demo.model.Material;
import com.inventario.demo.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public Material findById(Long id) {
        return materialRepository.findById(id).orElse(null);
    }
    public List<Material> buscarPorNombre(String nombre) {
        return materialRepository.findByNombreContainingIgnoreCase(nombre);
    }
    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }
}
