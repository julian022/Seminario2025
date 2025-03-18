package com.inventario.demo.services;

import com.inventario.demo.model.SolicitudCompra;
import com.inventario.demo.repository.SolicitudCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudCompraService {
    @Autowired
    private SolicitudCompraRepository solicitudCompraRepository;

    public List<SolicitudCompra> findAll() {
        return solicitudCompraRepository.findAll();
    }

    public SolicitudCompra save(SolicitudCompra solicitud) {
        return solicitudCompraRepository.save(solicitud);
    }

    public SolicitudCompra findById(Long id) {
        return solicitudCompraRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        solicitudCompraRepository.deleteById(id);
    }
}
