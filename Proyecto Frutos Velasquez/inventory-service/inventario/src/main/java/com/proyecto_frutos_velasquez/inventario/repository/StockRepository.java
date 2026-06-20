package com.proyecto_frutos_velasquez.inventario.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyecto_frutos_velasquez.inventario.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    Optional<Stock> findFirstByOrderByStockActualAsc();

    Optional<Stock> findByIdProducto(Long idProducto);

    List<Stock> findByStockActual(Double stockActual);

    List<Stock> findByStockActualLessThan(Double limite);

}
