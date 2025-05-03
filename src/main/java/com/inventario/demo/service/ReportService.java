package com.inventario.demo.service;

import com.inventario.demo.model.Material;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    private MaterialService materialService;

    // Método setter para inyectar el servicio en las pruebas
    public void setMaterialService(MaterialService materialService) {
        this.materialService = materialService;
    }

    public void exportMaterialsToExcel(HttpServletResponse response) throws IOException {
        List<Material> materiales = materialService.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Materiales");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Cantidad");
        headerRow.createCell(3).setCellValue("Stock Mínimo");
        headerRow.createCell(4).setCellValue("Stock Máximo");
        headerRow.createCell(5).setCellValue("Proveedor");

        int rowNum = 1;
        for (Material material : materiales) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(material.getId());
            row.createCell(1).setCellValue(material.getNombre());
            row.createCell(2).setCellValue(material.getCantidad());
            row.createCell(3).setCellValue(material.getStockMinimo());
            row.createCell(4).setCellValue(material.getStockMaximo());
            row.createCell(5).setCellValue(material.getProveedor().getNombre());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=materiales.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}



