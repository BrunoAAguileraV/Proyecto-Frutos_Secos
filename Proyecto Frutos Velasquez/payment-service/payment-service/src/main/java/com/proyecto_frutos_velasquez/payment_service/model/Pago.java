package com.proyecto_frutos_velasquez.payment_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long ventaId; // ID proveniente de db_ventas
    private Double monto;
    private String metodoPago; // "TARJETA", "TRANSFERENCIA"
    private String estadoPago; // "APROBADO", "RECHAZADO"
    private String transaccionId; // Token falso de Transbank
    private LocalDateTime fechaPago;
}
