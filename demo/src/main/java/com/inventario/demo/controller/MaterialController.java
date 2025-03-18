package com.inventario.demo.controller;


import com.inventario.demo.model.Material;
import com.inventario.demo.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.findAll();
    }

    @PostMapping
    public Material createMaterial(@RequestBody Material material) {
        return materialService.save(material);
    }

    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id) {
        return materialService.findById(id);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material materialDetails) {
        Material material = materialService.findById(id);
        if (material != null) {
            material.setNombre(materialDetails.getNombre());
            material.setCantidad(materialDetails.getCantidad());
            material.setStockMinimo(materialDetails.getStockMinimo());
            material.setStockMaximo(materialDetails.getStockMaximo());
            material.setProveedor(materialDetails.getProveedor());
            return materialService.save(material);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
    }
}
