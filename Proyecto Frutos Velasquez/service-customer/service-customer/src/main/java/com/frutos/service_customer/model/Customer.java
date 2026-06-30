package com.frutos.service_customer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa el perfil y datos de despacho de un cliente")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autoincremental del cliente en la tabla", example = "1")
    private Long id;

    @NotNull(message = "El ID de usuario de autenticación es obligatorio")
    @Schema(description = "Vínculo con el ID del usuario de db_seguridad", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(min = 9, max = 10, message = "El RUT debe tener entre 9 y 10 caracteres con guion")
    @Schema(description = "RUT del cliente con guion", example = "12345678-9", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(unique = true)
    private String rut;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre completo del cliente", example = "Oliver Vidal", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un formato de correo electrónico válido")
    @Schema(description = "Correo de contacto del cliente", example = "oliver@frutos.cl")
    @Column(unique = true)
    private String correo;

    @NotBlank(message = "La dirección de envío es obligatoria")
    @Schema(description = "Dirección para despachar los frutos secos", example = "Av. Siempre Viva 123, Santiago")
    private String direccion;

    @Schema(description = "Teléfono móvil o de red fija", example = "+56912345678")
    private String telefono;
}