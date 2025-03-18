package com.inventario.demo.services;


import com.inventario.demo.model.OrdenCompra;
import com.inventario.demo.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra save(OrdenCompra orden) {
        return ordenCompraRepository.save(orden);
    }

    public OrdenCompra findById(Long id) {
        return ordenCompraRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        ordenCompraRepository.deleteById(id);
    }
}
