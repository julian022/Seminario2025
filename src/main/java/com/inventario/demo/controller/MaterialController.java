package com.inventario.demo.controller;


import com.inventario.demo.model.Material;
import com.inventario.demo.service.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping(Path="/listarmateriales")
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materiales = materialService.findAll();
        return ResponseEntity.ok(materiales);
    }

    @PostMapping(Path="/crearmaterial")
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material saved = materialService.save(material);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping(Path="/findbyid/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        Material material = materialService.findById(id);
        if (material != null) {
            return ResponseEntity.ok(material);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(Path="actualizarmaterial/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Long id, @RequestBody Material materialDetails) {
        Material material = materialService.findById(id);
        if (material != null) {
            material.setNombre(materialDetails.getNombre());
            material.setCantidad(materialDetails.getCantidad());
            material.setStockMinimo(materialDetails.getStockMinimo());
            material.setStockMaximo(materialDetails.getStockMaximo());
            material.setProveedor(materialDetails.getProveedor());

            Material updated = materialService.save(material);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Material>> buscar(@RequestParam String nombre) {
        List<Material> resultado = materialService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultado);
    }
}
