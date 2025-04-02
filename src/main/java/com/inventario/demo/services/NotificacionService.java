package com.inventario.demo.services;

import com.inventario.demo.model.Material;

public interface NotificationService {
    void enviarAlertaStockBajo(Material material);
    void enviarAlertaStockCritico(Material material);
    void enviarAlertaStockAgotado(Material material);
}