package com.proyecto_frutos_secos.shipping_service.controller;

import com.proyecto_frutos_secos.shipping_service.model.Despacho;
import com.proyecto_frutos_secos.shipping_service.service.DespachoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shippings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Despachos", description = "Seguimiento logístico de órdenes")
public class DespachoController {
    private final DespachoService despachoService;

    @GetMapping
    @Operation(summary = "Listar todos los despachos")
    public ResponseEntity<List<Despacho>> listarTodo(){
        return ResponseEntity.ok(despachoService.listarDespachos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar despacho por ID")
    public ResponseEntity<Optional<Despacho>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(despachoService.buscarPorId(id));
    }


    @PostMapping
    @Operation(summary = "Generar orden de despacho para una venta")
    public ResponseEntity<Despacho> crear(@RequestBody Despacho despacho) {
        return ResponseEntity.ok(despachoService.registrarDespacho(despacho));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado logístico (Ej: EN_TRANSITO)")
    public ResponseEntity<Despacho> actualizar(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(despachoService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro de despacho por ID")
    public ResponseEntity<Void> eliminarDespacho(@PathVariable Long id) {
        despachoService.eliminarDespacho(id);
        return ResponseEntity.noContent().build();
}
}
