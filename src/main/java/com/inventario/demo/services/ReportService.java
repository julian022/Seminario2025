package com.inventario.demo.services;

import com.inventario.demo.model.Material;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private MaterialService materialService;

    // Método que genera y exporta el archivo Excel con los materiales
    public void exportMaterialsToExcel(HttpServletResponse response) throws IOException {
        // Obtener todos los materiales
        List<Material> materiales = materialService.findAll();

        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Materiales");

        // Crear encabezado de las columnas
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Cantidad");
        headerRow.createCell(3).setCellValue("Stock Mínimo");
        headerRow.createCell(4).setCellValue("Stock Máximo");
        headerRow.createCell(5).setCellValue("Proveedor");

        // Llenar los datos
        int rowNum = 1;
        for (Material material : materiales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(material.getId());
            row.createCell(1).setCellValue(material.getNombre());
            row.createCell(2).setCellValue(material.getCantidad());
            row.createCell(3).setCellValue(material.getStockMinimo());
            row.createCell(4).setCellValue(material.getStockMaximo());
            row.createCell(5).setCellValue(material.getProveedor().getNombre());  // Asumiendo que 'Proveedor' tiene el atributo 'nombre'
        }

        // Configurar la respuesta HTTP para que el archivo Excel sea descargado
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=materiales.xlsx");

        // Escribir el archivo Excel a la respuesta
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}


