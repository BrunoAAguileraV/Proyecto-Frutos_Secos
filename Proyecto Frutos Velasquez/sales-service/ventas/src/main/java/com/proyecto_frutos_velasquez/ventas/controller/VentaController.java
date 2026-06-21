package com.proyecto_frutos_velasquez.ventas.controller;

import com.proyecto_frutos_velasquez.ventas.model.Venta;
import com.proyecto_frutos_velasquez.ventas.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ventas")
@CrossOrigin(origins = "*")
@Tag(name = "Ventas", description = "Endpoints para gestionar las ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        try {
            // Si la venta sale bien, devolvemos un 201 CREATED con la boleta
            Venta nuevaVenta = ventaService.procesarVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (RuntimeException e) {
            // Si el Service tira un error (ej. el producto no existe en Catálogo),
            // atrapamos el error y devolvemos un 400 Bad Request con el mensaje.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/total-dia/{fecha}")
    public ResponseEntity<Double> obtenerTotalDia(
            // Le pedimos a Spring que convierta el String de la URL a LocalDate automáticamente
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        
        Double total = ventaService.obtenerTotalVentasDia(fecha);
        
        // Si el repositorio devuelve null (porque no se vendió nada ese día), 
        // mandamos un 0.0 de forma segura.
        return ResponseEntity.ok(total != null ? total : 0.0);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una venta por ID")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build(); 
}
}
