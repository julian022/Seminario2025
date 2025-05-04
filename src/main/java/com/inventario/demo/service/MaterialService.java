package com.inventario.demo.service;

import com.inventario.demo.model.Material;
import com.inventario.demo.repository.MaterialRepository;
import com.inventario.demo.repository.ProveedorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final ProveedorRepository proveedorRepository;

   // Constructor que inicializa ambos repositorios
    public MaterialService(MaterialRepository materialRepository, ProveedorRepository proveedorRepository) {
        this.materialRepository = materialRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material save(Material material) {
        Long proveedorId = material.getProveedor() != null ? material.getProveedor().getId() : null;

        if (proveedorId == null || !proveedorRepository.existsById(proveedorId)) {
            throw new IllegalArgumentException("El proveedor especificado no existe.");
        }
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
