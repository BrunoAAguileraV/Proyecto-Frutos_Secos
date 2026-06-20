package com.proyecto_frutos_velasquez.analiticas.controller;

import com.proyecto_frutos_velasquez.analiticas.model.ResumenDiario;
import com.proyecto_frutos_velasquez.analiticas.service.AnaliticaService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/analitica")
@CrossOrigin(origins = "*")
@Tag(name = "Analiticas", description = "Endpoints para gestionar las analiticas")
public class AnaliticaController {

    @Autowired
    private AnaliticaService analiticaService;

    @GetMapping("/reporte")
    public ResponseEntity<ResumenDiario> obtenerReporte(
            // Le decimos a Spring que espere una fecha formato YYYY-MM-DD
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        
        // Ejecutamos el servicio, el cual ahora está protegido contra los 404 de Ventas
        ResumenDiario reporte = analiticaService.generarReporteDiario(fecha);
        
        // Devolvemos el reporte con un código HTTP 200 (OK)
        return ResponseEntity.ok(reporte);
    }
}
