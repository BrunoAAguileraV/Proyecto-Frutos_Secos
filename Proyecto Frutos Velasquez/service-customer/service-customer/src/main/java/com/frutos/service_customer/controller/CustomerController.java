package com.frutos.service_customer.controller;

import com.frutos.service_customer.model.Customer;
import com.frutos.service_customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*") 
@Tag(name = "Clientes", description = "Gestión integral de perfiles de clientes y datos de envío")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Listar todos los clientes", description = "Obtiene la lista completa de registros de clientes")
    @GetMapping
    public ResponseEntity<List<Customer>> listar() {
        return ResponseEntity.ok(customerService.listarTodos());
    }

    @Operation(summary = "Buscar cliente por RUT", description = "Obtiene la información de un cliente mediante su RUT único")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Customer> obtenerPorRut(@PathVariable String rut) {
        return customerService.buscarPorRut(rut)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con RUT: " + rut));
    }

    @Operation(summary = "Obtener perfil por ID de Usuario Auth", description = "Busca los datos de despacho vinculados al Login de la app")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Customer> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return customerService.buscarPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("No se encontró perfil de cliente para el Usuario ID: " + usuarioId));
    }

    @Operation(summary = "Registrar o actualizar cliente", description = "Guarda los datos aplicando validaciones de formato en el RUT y Correo")
    @PostMapping
    public ResponseEntity<Customer> guardar(@Valid @RequestBody Customer customer) {
        Customer clienteGuardado = customerService.guardarOActualizar(customer);
        return new ResponseEntity<>(clienteGuardado, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente de la base de datos por ID")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        customerService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
}
}