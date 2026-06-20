package com.proyecto_frutos_velasquez.analiticas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "resumen_diario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;
    
    private LocalDate fecha;
    private Double ingresosTotales;
    private Double costosTotales;
    private Double utilidadNeta;
}