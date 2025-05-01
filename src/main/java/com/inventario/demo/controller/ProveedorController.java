package com.inventario.demo.controller;


import com.inventario.demo.model.Proveedor;
import com.inventario.demo.service.ProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorService.findAll();
    }

    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    @GetMapping("/{id}")
    public Proveedor getProveedorById(@PathVariable Long id) {
        return proveedorService.findById(id);
    }

    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor != null) {
            proveedor.setNombre(proveedorDetails.getNombre());
            proveedor.setContacto(proveedorDetails.getContacto());
            return proveedorService.save(proveedor);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorService.deleteById(id);
    }
}
