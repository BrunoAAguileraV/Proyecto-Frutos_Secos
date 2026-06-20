package com.frutos.service_notification.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Registro de notificaciones enviadas a los clientes")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la notificación", example = "1")
    private Long id;

    @NotBlank(message = "El correo de destino es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Schema(description = "Correo del cliente que recibe la alerta", example = "oliver@frutos.cl")
    private String correoDestino;

    @NotBlank(message = "El asunto no puede estar vacío")
    @Schema(description = "Asunto del correo", example = "¡Tu pedido ha sido despachado!")
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio")
    @Column(columnDefinition = "TEXT")
    @Schema(description = "Cuerpo del mensaje", example = "Hola, tus frutos secos van en camino a tu domicilio.")
    private String mensaje;

    @Schema(description = "Fecha y hora exacta del envío")
    private LocalDateTime fechaEnvio = LocalDateTime.now();
}