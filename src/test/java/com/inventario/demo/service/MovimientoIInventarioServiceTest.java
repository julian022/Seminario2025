package com.inventario.demo.service;

import com.inventario.demo.model.MovimientoInventario;
import com.inventario.demo.repository.MovimientoInventarioRepository;

import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MovimientoInventarioServiceTest {

    private MovimientoInventarioRepository repository;
    private MovimientoInventarioService service;

    @Test
    void testFindByIdFound() {
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setId(1L);
        movimiento.setCantidad(10);
        movimiento.setTipoMovimiento("ENTRADA");
    
        when(repository.findById(1L)).thenReturn(Optional.of(movimiento));
    
        MovimientoInventario resultado = service.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(10, resultado.getCantidad());
        assertEquals("ENTRADA", resultado.getTipoMovimiento());
    }
    
    @Test
    void testFindByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
    
        MovimientoInventario resultado = service.findById(99L);
        assertNull(resultado);
    }
    
    @Test
    void testDeleteById() {
        Long id = 5L;
        doNothing().when(repository).deleteById(id);
    
        service.deleteById(id);
    
        verify(repository, times(1)).deleteById(id);
    }
}