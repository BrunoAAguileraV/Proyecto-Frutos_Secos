package com.proyecto_frutos_velasquez.inventario.service;

import com.proyecto_frutos_velasquez.inventario.model.*;
import com.proyecto_frutos_velasquez.inventario.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MovStockRepository movimientoRepository;

    public List<Stock> listarTodo() { 
        return stockRepository.findAll();
    }

    public Stock guardar(Stock stock){
        return stockRepository.save(stock);
    }
    
    public Optional<Stock> buscarPorId(Long id) { 
        return stockRepository.findById(id); 
    }

    public Optional<Stock> buscarPorProducto(Long idProducto) { 
        return stockRepository.findByIdProducto(idProducto); 
    }

    public List<Stock> productosBajoStock(Double limite) { 
        return stockRepository.findByStockActualLessThan(limite); 
    }

    @Transactional
    public MovimientoStock registrarMovimiento(MovimientoStock movimiento) {
        // 1. Buscamos si ya existe un registro de stock para ese producto
        Stock stockActual = stockRepository.findByIdProducto(movimiento.getIdProducto())
                .orElse(new Stock(null, movimiento.getIdProducto(), 0.0)); // Si no existe, empezamos en 0

        // 2. Lógica de suma o resta según el tipo de movimiento
        if (movimiento.getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
            stockActual.setStockActual(stockActual.getStockActual() + movimiento.getCantidad());
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("SALIDA") || 
                   movimiento.getTipoMovimiento().equalsIgnoreCase("MERMA")) {
            stockActual.setStockActual(stockActual.getStockActual() - movimiento.getCantidad());
        }

        // 3. Guardamos el balance actualizado
        stockRepository.save(stockActual);

        // 4. Seteamos la fecha actual al movimiento y lo guardamos en el historial
        movimiento.setFechaHora(LocalDateTime.now());
        return movimientoRepository.save(movimiento);
    }

    public void eliminar(Long id) {
        stockRepository.deleteById(id);
    }
}
