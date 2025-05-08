package com.inventario.demo.controller;


import com.inventario.demo.model.SolicitudCompra;
import com.inventario.demo.service.SolicitudCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudCompraController {

    @Autowired
    private SolicitudCompraService solicitudCompraService;

    @GetMapping(path = "/listarSolicitudes")
    public List<SolicitudCompra> getAllSolicitudes() {
        return solicitudCompraService.findAll();
    }
    @PostMapping(path = "/crearSolicitud")
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudCompra createSolicitud(@RequestBody SolicitudCompra solicitud) {
        return solicitudCompraService.save(solicitud);
    }

    @GetMapping(path = "/buscarSolicitud/{id}")
    public SolicitudCompra getSolicitudById(@PathVariable Long id) {
        return solicitudCompraService.findById(id);
    }

    @PutMapping(path = "/actualizarSolicitud/{id}")
    public SolicitudCompra updateSolicitud(@PathVariable Long id, @RequestBody SolicitudCompra solicitudDetails){
        SolicitudCompra solicitud = solicitudCompraService.findById(id);
        if (solicitud != null) {
            solicitud.setCantidad(solicitudDetails.getCantidad());
            solicitud.setEstado(solicitudDetails.getEstado());
            solicitud.setMaterial(solicitudDetails.getMaterial());
            return solicitudCompraService.save(solicitud);
        }
        return null;
    }

    @DeleteMapping(path = "/eliminarSolicitud/{id}")
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudCompraService.deleteById(id);
    }
}
