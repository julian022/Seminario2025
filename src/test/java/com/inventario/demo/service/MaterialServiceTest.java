package com.inventario.demo.service;

/*import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.inventario.demo.entity.Material;
import com.inventario.demo.repository.MaterialRepository;
import com.inventario.demo.repository.ProveedorRepository;
import com.inventario.demo.service.MaterialService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
class MaterialServiceTest {

    private MaterialService service;

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MaterialService(materialRepository, proveedorRepository);
    }

    @Test
    void testFindAll() {
        Material m = new Material();
        m.setNombre("Clavos");
        m.setCantidad(50);

        when(repository.findAll()).thenReturn(Arrays.asList(m));

        List<Material> resultado = service.findAll();
        assertEquals(1, resultado.size());
        assertEquals("Clavos", resultado.get(0).getNombre());
    }

    @Test
    void testSave() {
        Material m = new Material();
        m.setNombre("Tornillos");

        when(repository.save(m)).thenReturn(m);

        Material resultado = service.save(m);
        assertNotNull(resultado);
        assertEquals("Tornillos", resultado.getNombre());
    }

    @Test
    void testFindByIdFound() {
        Material m = new Material();
        m.setId(1L);
        m.setNombre("Martillo");

        when(repository.findById(1L)).thenReturn(Optional.of(m));

        Material resultado = service.findById(1L);
        assertNotNull(resultado);
        assertEquals("Martillo", resultado.getNombre());
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Material resultado = service.findById(99L);
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
*/  