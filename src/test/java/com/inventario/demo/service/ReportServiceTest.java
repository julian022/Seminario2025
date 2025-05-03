package com.inventario.demo.service;

import com.inventario.demo.model.Material;
import com.inventario.demo.model.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportServiceTest {

    private MaterialService materialService;
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        materialService = mock(MaterialService.class);
        reportService = new ReportService();
        reportService.setMaterialService(materialService); // Usar el setter para inyectar el mock
    }

    @Test
    void testExportMaterialsToExcel() throws Exception {
        // Crear datos simulados
        Material material1 = new Material();
        material1.setId(1L);
        material1.setNombre("Material A");
        material1.setCantidad(100);
        material1.setStockMinimo(10);
        material1.setStockMaximo(200);
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombre("Proveedor A");
        material1.setProveedor(proveedor1);

        Material material2 = new Material();
        material2.setId(2L);
        material2.setNombre("Material B");
        material2.setCantidad(50);
        material2.setStockMinimo(5);
        material2.setStockMaximo(100);
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setNombre("Proveedor B");
        material2.setProveedor(proveedor2);

        when(materialService.findAll()).thenReturn(Arrays.asList(material1, material2));

        // Simular la respuesta HTTP
        HttpServletResponse response = mock(HttpServletResponse.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ServletOutputStream outputStream = new ServletOutputStream() {
            @Override
            public void write(int b) {
                byteArrayOutputStream.write(b);
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                // No se necesita implementación para esta prueba
            }

            @Override
            public boolean isReady() {
                return true; // Siempre listo para escribir
            }
        };
        when(response.getOutputStream()).thenReturn(outputStream);

        // Ejecutar el método
        reportService.exportMaterialsToExcel(response);

        // Verificar que se escribieron datos en el flujo de salida
        verify(response, times(1)).setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        verify(response, times(1)).setHeader("Content-Disposition", "attachment; filename=materiales.xlsx");
        assertTrue(byteArrayOutputStream.size() > 0, "El flujo de salida debería contener datos");
    }
}