package com.proyecto_frutos_velasquez.catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo_medida;
    private int precio_venta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")//ESTO OBLIGA A TRAER LOS DATOS DE LA OTRA TABLA
    private Categoria categoria;


}
