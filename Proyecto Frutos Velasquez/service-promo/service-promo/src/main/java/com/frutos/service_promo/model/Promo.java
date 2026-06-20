package com.frutos.service_promo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promociones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un cupón de descuento en Frutos Velásquez")
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único autoincremental", example = "1")
    private Long id;

    @NotBlank(message = "El código del cupón es obligatorio")
    @Schema(description = "Código de texto del cupón", example = "MANI20", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(unique = true)
    private String codigo;

    @NotNull(message = "El porcentaje de descuento no puede ser nulo")
    @Min(value = 1, message = "El descuento mínimo es 1%")
    @Max(value = 100, message = "El descuento máximo es 100%")
    @Schema(description = "Porcentaje a descontar del total", example = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer porcentajeDescuento;

    @Schema(description = "Indica si la promoción sigue vigente", example = "true")
    private boolean activa = true;
}