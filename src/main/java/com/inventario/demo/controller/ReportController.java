package com.inventario.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.inventario.demo.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint para exportar materiales a Excel
    @GetMapping(path = "/materiales/exportar")
    public void exportMaterialsToExcel(HttpServletResponse response) throws IOException {
        reportService.exportMaterialsToExcel(response);
    }
}
