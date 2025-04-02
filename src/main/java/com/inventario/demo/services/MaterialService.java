package com.inventario.demo.services;

import com.inventario.demo.model.Material;
import com.inventario.demo.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final NotificationService notificationService;

    @Autowired
    public MaterialService(MaterialRepository materialRepository, 
                         NotificationService notificationService) {
        this.materialRepository = materialRepository;
        this.notificationService = notificationService;
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return materialRepository.existsById(id);
    }

    @Transactional
    public Material save(Material material) {
        // Guardar el material primero
        Material savedMaterial = materialRepository.save(material);
        
        // Verificar y enviar notificaciones según el estado del stock
        handleStockNotifications(savedMaterial);
        
        return savedMaterial;
    }

    @Transactional
    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }

    public List<Material> findByStockActualLessThanStockMinimo() {
        return materialRepository.findAll().stream()
                .filter(Material::isNecesitaReposicion)
                .toList();
    }

    public List<Material> findByStockActualLessThan(int cantidad) {
        return materialRepository.findAll().stream()
                .filter(m -> m.getStockActual() < cantidad)
                .toList();
    }

    // Métodos adicionales para manejo de inventario
    @Transactional
    public Material reducirStock(Long materialId, int cantidad) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        
        int nuevoStock = material.getStockActual() - cantidad;
        material.setStockActual(Math.max(nuevoStock, 0)); // No permitir stock negativo
        
        Material updatedMaterial = materialRepository.save(material);
        handleStockNotifications(updatedMaterial);
        
        return updatedMaterial;
    }

    @Transactional
    public Material aumentarStock(Long materialId, int cantidad) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        
        int nuevoStock = material.getStockActual() + cantidad;
        if (material.getStockMaximo() != null) {
            nuevoStock = Math.min(nuevoStock, material.getStockMaximo());
        }
        material.setStockActual(nuevoStock);
        
        Material updatedMaterial = materialRepository.save(material);
        handleStockNotifications(updatedMaterial);
        
        return updatedMaterial;
    }

    // Método privado para manejar las notificaciones
    private void handleStockNotifications(Material material) {
        switch (material.getNivelAlerta()) {
            case AGOTADO:
                notificationService.enviarAlertaStockAgotado(material);
                break;
            case CRITICO:
                notificationService.enviarAlertaStockCritico(material);
                break;
            case BAJO:
                notificationService.enviarAlertaStockBajo(material);
                break;
            case NORMAL:
                // No se requiere notificación
                break;
        }
    }
}