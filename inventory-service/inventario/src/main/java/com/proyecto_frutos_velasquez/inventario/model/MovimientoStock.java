package com.proyecto_frutos_velasquez.inventario.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimiento_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;

    private Long idProducto; 
    private String tipoMovimiento; // "ENTRADA", "SALIDA", "MERMA"
    private Double cantidad;
    private LocalDateTime fechaHora;
}
