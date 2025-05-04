package com.inventario.demo.controller;

import com.inventario.demo.exception.ResourceNotFoundException;
import com.inventario.demo.model.Proveedor;
import com.inventario.demo.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.ok(nuevoProveedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + id);
        }
        return ResponseEntity.ok(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + id);
        }

        proveedor.setNombre(proveedorDetails.getNombre());
        proveedor.setContacto(proveedorDetails.getContacto());

        Proveedor actualizado = proveedorService.save(proveedor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) {
            throw new ResourceNotFoundException("Proveedor no encontrado con id: " + id);
        }

        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
