package com.inventario.demo.controller;


import com.inventario.demo.model.SolicitudCompra;
import com.inventario.demo.service.SolicitudCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudCompraController {

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @GetMapping
    public List<SolicitudCompra> getAllSolicitudes() {
        return solicitudCompraService.findAll();
    }

    @PostMapping
    public SolicitudCompra createSolicitud(@RequestBody SolicitudCompra solicitud) {
        return solicitudCompraService.save(solicitud);
    }

    @GetMapping("/{id}")
    public SolicitudCompra getSolicitudById(@PathVariable Long id) {
        return solicitudCompraService.findById(id);
    }

    @PutMapping("/{id}")
    public SolicitudCompra updateSolicitud(@PathVariable Long id, @RequestBody SolicitudCompra solicitudDetails) {
        SolicitudCompra solicitud = solicitudCompraService.findById(id);
        if (solicitud != null) {
            solicitud.setCantidad(solicitudDetails.getCantidad());
            solicitud.setEstado(solicitudDetails.getEstado());
            solicitud.setMaterial(solicitudDetails.getMaterial());
            return solicitudCompraService.save(solicitud);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudCompraService.deleteById(id);
    }
}
