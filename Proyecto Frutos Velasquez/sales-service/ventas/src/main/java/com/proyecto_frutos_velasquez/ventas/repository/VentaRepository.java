package com.proyecto_frutos_velasquez.ventas.repository;

import com.proyecto_frutos_velasquez.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // Consulta para el micro de Analítica (RF08)
    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fechaHora BETWEEN :inicio AND :fin")
    Double sumarTotalPorRango(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}

