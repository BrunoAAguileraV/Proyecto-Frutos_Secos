package com.proyecto_frutos_velasquez.compras.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    private String contacto;
}
