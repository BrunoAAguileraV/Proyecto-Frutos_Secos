package com.proyecto_frutos_velasquez.compras.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "compra_proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @Column(name = "id_producto")
    private Long idProducto; // Referencia externa al Catálogo

    @Column(name = "id_proveedor")
    private Long idProveedor; // Relación con el proveedor que nos vendió

    @Column(name = "costo_compra")
    private Double costoCompra;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;
}
