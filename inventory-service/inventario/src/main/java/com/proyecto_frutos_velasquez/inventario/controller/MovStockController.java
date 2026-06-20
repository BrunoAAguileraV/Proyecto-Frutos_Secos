package com.proyecto_frutos_velasquez.inventario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto_frutos_velasquez.inventario.model.MovimientoStock;
import com.proyecto_frutos_velasquez.inventario.service.MovimientoStockService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventario/movimientos")
@CrossOrigin(origins = "*")
@Tag(name = "Movimiento stock", description = "Endpoints para gestionar los movimientos del stock")
public class MovStockController {

    @Autowired
    private MovimientoStockService movStockService;

    // 1. Listar todos los movimientos (Para el historial general)
    @GetMapping
    public List<MovimientoStock> listarTodo() {
        return movStockService.listarTodo();
    }

    // 2. Buscar un movimiento específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoStock> obtenerPorId(@PathVariable Long id) {
        return movStockService.encontrarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{idProducto}")
    public List<MovimientoStock> listarPorProducto(@PathVariable Long idProducto) {
        return movStockService.buscarPorProducto(idProducto);
    }

    @PostMapping
    public ResponseEntity<MovimientoStock> registrar(@RequestBody MovimientoStock movimiento) {
        try {
            // Aquí el Service debería encargarse de actualizar también la tabla de Stock
            MovimientoStock nuevoMov = movStockService.guardarMovimiento(movimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMov);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}