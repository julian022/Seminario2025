package com.inventario.demo.controller;

import com.inventario.demo.model.OrdenCompra;
import com.inventario.demo.services.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping
    public List<OrdenCompra> getAllOrdenes() {
        return ordenCompraService.findAll();
    }

    @PostMapping
    public OrdenCompra createOrden(@RequestBody OrdenCompra orden) {
        return ordenCompraService.save(orden);
    }

    @GetMapping("/{id}")
    public OrdenCompra getOrdenById(@PathVariable Long id) {
        return ordenCompraService.findById(id);
    }

    @PutMapping("/{id}")
    public OrdenCompra updateOrden(@PathVariable Long id, @RequestBody OrdenCompra ordenDetails) {
        OrdenCompra orden = ordenCompraService.findById(id);
        if (orden != null) {
            orden.setSolicitudCompra(ordenDetails.getSolicitudCompra());
            return ordenCompraService.save(orden);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        ordenCompraService.deleteById(id);
    }
}
