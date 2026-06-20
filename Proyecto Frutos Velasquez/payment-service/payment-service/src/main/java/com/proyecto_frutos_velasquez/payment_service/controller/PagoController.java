package com.proyecto_frutos_velasquez.payment_service.controller;

import com.proyecto_frutos_velasquez.payment_service.model.Pago;
import com.proyecto_frutos_velasquez.payment_service.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "Simulador de pasarela de pagos")
public class PagoController {
    private final PagoService pagoService;

    @PostMapping("/procesar")
    @Operation(summary = "Procesar pago de una venta")
    public ResponseEntity<Pago> procesar(@RequestBody Pago pago) {
        return ResponseEntity.ok(pagoService.procesarPago(pago));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro de pago por ID")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
}
}