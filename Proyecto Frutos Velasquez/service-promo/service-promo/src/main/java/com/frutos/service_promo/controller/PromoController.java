package com.frutos.service_promo.controller;

import com.frutos.service_promo.model.Promo;
import com.frutos.service_promo.service.PromoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promos")
@CrossOrigin(origins = "*") // Permite que el Gateway o Swagger lo llamen
@Tag(name = "Promociones", description = "Operaciones para gestionar los cupones de descuento")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Operation(summary = "Listar promociones", description = "Retorna todos los cupones registrados")
    @GetMapping
    public ResponseEntity<List<Promo>> listar() {
        return ResponseEntity.ok(promoService.listarTodas());
    }

    @Operation(summary = "Buscar cupón", description = "Busca un cupón específico por su código (Ej: MANI20)")
    @GetMapping("/{codigo}")
    public ResponseEntity<Promo> obtenerPorCodigo(@PathVariable String codigo) {
        return promoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Cupón no encontrado o inválido"));
    }

    @Operation(summary = "Crear cupón", description = "Guarda una nueva promoción validando los datos")
    @PostMapping
    public ResponseEntity<Promo> crear(@Valid @RequestBody Promo promo) {
        Promo nuevaPromo = promoService.guardar(promo);
        return new ResponseEntity<>(nuevaPromo, HttpStatus.CREATED);
    }
}