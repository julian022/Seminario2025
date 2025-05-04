package com.inventario.demo.service;

import com.inventario.demo.model.MovimientoInventario;
import com.inventario.demo.repository.MovimientoInventarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovimientoInventarioServiceTest {

    @Mock
    private MovimientoInventarioRepository repository;

    @InjectMocks
    private MovimientoInventarioService service;

    private MovimientoInventario movimiento;

    @BeforeEach
    public void setUp() {
        movimiento = new MovimientoInventario();
        movimiento.setId(1L);
        movimiento.setCantidad(10);
    }

    @Test
    void testFindByIdFound() {
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
