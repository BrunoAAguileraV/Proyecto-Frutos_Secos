package com.proyecto_frutos_velasquez.inventario.repository;

import com.proyecto_frutos_velasquez.inventario.model.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovStockRepository extends JpaRepository<MovimientoStock, Long> {

    /**
     * Busca todo el historial de movimientos de un producto específico.
     * Vital para el RF08 (Reportes por producto).
     */
    List<MovimientoStock> findByIdProducto(Long idProducto);

    /**
     * Filtra movimientos por tipo (ENTRADA, SALIDA, MERMA).
     * Útil para auditar pérdidas o ver el volumen de ventas.
     */
    List<MovimientoStock> findByTipoMovimiento(String tipoMovimiento);

    /**
     * Obtiene los últimos movimientos registrados.
     * Ideal para mostrar una "Actividad Reciente" en tu Dashboard.
     */
    List<MovimientoStock> findTop10ByOrderByIdMovimientoDesc();
}
