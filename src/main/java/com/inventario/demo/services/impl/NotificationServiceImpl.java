package com.inventario.demo.services;

import com.inventario.demo.model.Material;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void enviarAlertaStockBajo(Material material) {
        // Implementación real para enviar notificaciones
        System.out.println("ALERTA: Stock bajo para " + material.getNombre() + 
                          " - Stock actual: " + material.getStockActual() + 
                          ", Mínimo requerido: " + material.getStockMinimo());
    }

    @Override
    public void enviarAlertaStockCritico(Material material) {
        System.out.println("ALERTA CRÍTICA: Stock muy bajo para " + material.getNombre() + 
                          " - Stock actual: " + material.getStockActual());
    }

    @Override
    public void enviarAlertaStockAgotado(Material material) {
        System.out.println("ALERTA DE EMERGENCIA: Stock agotado para " + material.getNombre());
    }
}