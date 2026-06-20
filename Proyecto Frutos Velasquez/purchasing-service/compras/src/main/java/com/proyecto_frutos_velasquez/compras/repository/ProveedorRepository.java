package com.proyecto_frutos_velasquez.compras.repository;

import com.proyecto_frutos_velasquez.compras.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    // findById ya viene por defecto
}
