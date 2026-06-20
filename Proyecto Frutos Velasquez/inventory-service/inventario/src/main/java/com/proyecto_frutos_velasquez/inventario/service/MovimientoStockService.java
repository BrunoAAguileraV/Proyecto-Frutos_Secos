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
public class MovimientoStockService {

    @Autowired
    private MovStockRepository movRepository;

    @Autowired
    private StockRepository stockRepository;

    public List<MovimientoStock> listarTodo() {
        return movRepository.findAll();
    }

    public Optional<MovimientoStock> encontrarPorId(Long id) {
        return movRepository.findById(id);
    }

    public List<MovimientoStock> buscarPorProducto(Long idProducto) {
        return movRepository.findByIdProducto(idProducto);
    }

    @Transactional // ¡VITAL! Si falla uno, se deshace todo.
    public MovimientoStock guardarMovimiento(MovimientoStock movimiento) {
        // 1. Seteamos la fecha actual automáticamente
        movimiento.setFechaHora(LocalDateTime.now());

        // 2. Buscamos el stock actual de ese producto
        Stock stock = stockRepository.findByIdProducto(movimiento.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Error: No existe registro de stock para el producto " + movimiento.getIdProducto()));

        // 3. Lógica de negocio: Actualizamos el stock según el tipo de movimiento
        if (movimiento.getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
            stock.setStockActual(stock.getStockActual() + movimiento.getCantidad());
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("SALIDA") || 
                   movimiento.getTipoMovimiento().equalsIgnoreCase("MERMA")) {
            
            // Validamos que no quede stock negativo
            if (stock.getStockActual() < movimiento.getCantidad()) {
                throw new RuntimeException("Error: Stock insuficiente para realizar la salida.");
            }
            stock.setStockActual(stock.getStockActual() - movimiento.getCantidad());
        }

        // 4. Guardamos el stock actualizado
        stockRepository.save(stock);

        // 5. Guardamos y retornamos el movimiento registrado
        return movRepository.save(movimiento);
    }
}
