package com.proyecto_frutos_velasquez.compras.controller;

import com.proyecto_frutos_velasquez.compras.model.CompraProveedor;
import com.proyecto_frutos_velasquez.compras.service.CompraProveedorService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compras/historial")
@CrossOrigin(origins = "*")
@Tag(name = "Compras Proveedor", description = "Endpoints para gestionar las compras a los proveedores")
public class CompraProveedorController {

    @Autowired
    private CompraProveedorService compraService;

    @GetMapping
    public List<CompraProveedor> listar() {
        return compraService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraProveedor> buscarId(@PathVariable Long id) {
        return compraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{idProducto}")
    public List<CompraProveedor> buscarPorProducto(@PathVariable Long idProducto) {
        return compraService.buscarPorProducto(idProducto);
    }

    @PostMapping
    public ResponseEntity<CompraProveedor> registrar(@RequestBody CompraProveedor compra) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compraService.registrarCompra(compra));
    }
}
