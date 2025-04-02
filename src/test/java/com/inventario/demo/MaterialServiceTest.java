package com.inventario.demo;

import com.inventario.demo.model.Material;
import com.inventario.demo.repository.MaterialRepository;
import com.inventario.demo.services.MaterialService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Datos simulados
        Material material1 = new Material(1L, "Madera", 10, 2, 20, null);
        Material material2 = new Material(2L, "Acero", 5, 1, 15, null);

        when(materialRepository.findAll()).thenReturn(Arrays.asList(material1, material2));

        List<Material> materiales = materialService.findAll();

        assertNotNull(materiales);
        assertEquals(2, materiales.size());
    }

    @Test
    void testFindById() {
        Material material = new Material(1L, "Madera", 10, 2, 20, null);

        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        Material foundMaterial = materialService.findById(1L);

        assertNotNull(foundMaterial);
        assertEquals("Madera", foundMaterial.getNombre());
    }

    @Test
    void testSave() {
        Material material = new Material(1L, "Madera", 10, 2, 20, null);

        when(materialRepository.save(any(Material.class))).thenReturn(material);

        Material savedMaterial = materialService.save(material);

        assertNotNull(savedMaterial);
        assertEquals("Madera", savedMaterial.getNombre());
    }

    @Test
    void testDeleteById() {
        doNothing().when(materialRepository).deleteById(1L);

        assertDoesNotThrow(() -> materialService.deleteById(1L));

        verify(materialRepository, times(1)).deleteById(1L);
    }
}
