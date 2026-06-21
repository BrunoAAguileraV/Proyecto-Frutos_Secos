package com.proyecto_frutos_velasquez.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_frutos_velasquez.catalogo.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Categoria findByNombreIgnoreCase(String nombre);

}

