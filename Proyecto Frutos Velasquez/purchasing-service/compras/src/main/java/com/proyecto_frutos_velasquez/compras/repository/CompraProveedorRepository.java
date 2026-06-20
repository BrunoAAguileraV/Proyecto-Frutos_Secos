package com.proyecto_frutos_velasquez.compras.repository;

import com.proyecto_frutos_velasquez.compras.model.CompraProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraProveedorRepository extends JpaRepository<CompraProveedor, Long> {

    // Para ver todo lo que le hemos comprado a un proveedor específico
    List<CompraProveedor> findByIdProveedor(Long idProveedor);

    // Para ver el historial de costos de un fruto seco específico
    List<CompraProveedor> findByIdProducto(Long idProducto);

    // Vital para el reporte analítico (RF10)
    List<CompraProveedor> findByFechaCompraBetween(LocalDate inicio, LocalDate fin);
}
