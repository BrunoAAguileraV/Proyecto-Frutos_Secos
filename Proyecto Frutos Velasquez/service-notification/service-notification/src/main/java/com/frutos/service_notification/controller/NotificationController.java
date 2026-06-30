package com.frutos.service_notification.controller;

import com.frutos.service_notification.model.Notification;
import com.frutos.service_notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*")
@Tag(name = "Notificaciones", description = "Gestión del historial de correos y alertas enviadas")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "Ver historial completo", description = "Obtiene todas las notificaciones enviadas por el sistema")
    @GetMapping
    public ResponseEntity<List<Notification>> listar() {
        return ResponseEntity.ok(notificationService.listarTodas());
    }

    @Operation(summary = "Ver historial por cliente", description = "Obtiene las notificaciones enviadas a un correo específico")
    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Notification>> obtenerPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(notificationService.buscarPorCorreo(correo));
    }

    @Operation(summary = "Registrar nueva notificación", description = "Guarda un registro de alerta enviada")
    @PostMapping
    public ResponseEntity<Notification> crear(@Valid @RequestBody Notification notification) {
        Notification guardada = notificationService.registrarNotificacion(notification);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }
}