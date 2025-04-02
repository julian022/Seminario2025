package com.inventario.demo.services;

import com.inventario.demo.model.Material;
import com.inventario.demo.services.MaterialService;
import com.inventario.demo.controller.MaterialController;
import com.inventario.demo.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialServiceTest {

    @Mock
    private MaterialService materialService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private MaterialController materialController;

    private Material materialNormal;
    private Material materialStockBajo;
    private Material materialStockCritico;
    private Material materialSinStock;

    @BeforeEach
    void setUp() {
        // Configuración inicial de materiales para pruebas
        materialNormal = new Material(1L, "Cemento", 50, 10, 100, null);
        materialStockBajo = new Material(2L, "Arena", 8, 10, 100, null);
        materialStockCritico = new Material(3L, "Piedra", 2, 10, 50, null);
        materialSinStock = new Material(4L, "Tornillos", 0, 5, 200, null);
    }

    // Pruebas CRUD básicas
    @Test
    void findAllMaterials_ShouldReturnAllMaterials() {
        when(materialService.findAll()).thenReturn(Arrays.asList(materialNormal, materialStockBajo));
        
        ResponseEntity<List<Material>> response = materialController.getAllMaterials();
        
        assertFalse(response.getBody().isEmpty());
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(materialService, times(1)).findAll();
    }

    @Test
    void getMaterialById_WhenExists_ShouldReturnMaterial() {
        when(materialService.findById(1L)).thenReturn(Optional.of(materialNormal));
        
        ResponseEntity<Material> response = materialController.getMaterialById(1L);
        
        assertNotNull(response.getBody());
        assertEquals("Cemento", response.getBody().getNombre());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getMaterialById_WhenNotExists_ShouldReturnNotFound() {
        when(materialService.findById(99L)).thenReturn(Optional.empty());
        
        ResponseEntity<Material> response = materialController.getMaterialById(99L);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createMaterial_ShouldSaveAndReturnCreatedMaterial() {
        when(materialService.save(any(Material.class))).thenReturn(materialNormal);
        
        ResponseEntity<Material> response = materialController.createMaterial(materialNormal);
        
        assertNotNull(response.getBody());
        assertEquals("Cemento", response.getBody().getNombre());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Pruebas para el sistema de alertas de stock
    @Test
    void createMaterial_WithLowStock_ShouldSendNotification() {
        when(materialService.save(any(Material.class))).thenReturn(materialStockBajo);
        
        materialController.createMaterial(materialStockBajo);
        
        verify(notificationService, times(1))
            .enviarAlertaStockBajo(materialStockBajo);
    }

    @Test
    void createMaterial_WithCriticalStock_ShouldSendCriticalNotification() {
        when(materialService.save(any(Material.class))).thenReturn(materialStockCritico);
        
        materialController.createMaterial(materialStockCritico);
        
        verify(notificationService, times(1))
            .enviarAlertaStockCritico(materialStockCritico);
    }

    @Test
    void createMaterial_WithNoStock_ShouldSendEmergencyNotification() {
        when(materialService.save(any(Material.class))).thenReturn(materialSinStock);
        
        materialController.createMaterial(materialSinStock);
        
        verify(notificationService, times(1))
            .enviarAlertaStockAgotado(materialSinStock);
    }

    @Test
    void updateMaterial_ToLowStock_ShouldSendNotification() {
        Material updatedMaterial = new Material(1L, "Cemento", 5, 10, 100, null);
        when(materialService.existsById(1L)).thenReturn(true);
        when(materialService.save(any(Material.class))).thenReturn(updatedMaterial);
        
        materialController.updateMaterial(1L, updatedMaterial);
        
        verify(notificationService, times(1))
            .enviarAlertaStockBajo(updatedMaterial);
    }

    @Test
    void getMaterialsWithLowStock_ShouldReturnOnlyLowStockMaterials() {
        when(materialService.findByStockActualLessThanStockMinimo())
            .thenReturn(Arrays.asList(materialStockBajo, materialStockCritico, materialSinStock));
        
        ResponseEntity<List<Material>> response = materialController.getMaterialesConStockBajo();
        List<Material> materiales = response.getBody();
        
        assertEquals(3, materiales.size());
        assertTrue(materiales.stream().allMatch(m -> m.getStockActual() < m.getStockMinimo()));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getMaterialsWithCriticalStock_ShouldReturnOnlyCriticalStockMaterials() {
        when(materialService.findByStockActualLessThan(5)) // Umbral crítico
            .thenReturn(Arrays.asList(materialStockCritico, materialSinStock));
        
        ResponseEntity<List<Material>> response = materialController.getMaterialesConStockCritico();
        List<Material> materiales = response.getBody();
        
        assertEquals(2, materiales.size());
        assertTrue(materiales.stream().allMatch(m -> m.getStockActual() < 5));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}