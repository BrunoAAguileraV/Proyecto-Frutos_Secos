package com.proyecto_frutos_secos.shipping_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "despachos")
@Data
public class Despacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long ventaId; 
    private String direccionDespacho;
    private String estadoDespacho; // "PREPARACION", "EN_TRANSITO", "ENTREGADO"
    private String numeroSeguimiento;
    private LocalDateTime fechaDespacho;
}
