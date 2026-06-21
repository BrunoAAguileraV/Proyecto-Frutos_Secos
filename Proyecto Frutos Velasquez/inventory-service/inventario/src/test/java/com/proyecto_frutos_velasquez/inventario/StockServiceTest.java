package com.proyecto_frutos_velasquez.inventario;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto_frutos_velasquez.inventario.model.Stock;
import com.proyecto_frutos_velasquez.inventario.repository.StockRepository;
import com.proyecto_frutos_velasquez.inventario.service.StockService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void testBuscarPorProducto_Exito() {
        // Arrange
        Stock mockStock = new Stock();
        mockStock.setIdStock(1L);
        mockStock.setIdProducto(100L);
        mockStock.setStockActual(50.0);

        when(stockRepository.findByIdProducto(100L)).thenReturn(Optional.of(mockStock));

        // Act
        Optional<Stock> resultado = stockService.buscarPorProducto(100L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(50.0, resultado.get().getStockActual());
    }
}