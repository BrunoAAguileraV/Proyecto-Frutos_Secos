package com.proyecto_frutos_velasquez.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_frutos_velasquez.catalogo.model.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoriaId(Long id_categoria);

}
