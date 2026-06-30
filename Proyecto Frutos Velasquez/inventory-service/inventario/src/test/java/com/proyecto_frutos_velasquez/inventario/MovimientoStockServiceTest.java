package com.proyecto_frutos_velasquez.inventario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto_frutos_velasquez.inventario.model.MovimientoStock;
import com.proyecto_frutos_velasquez.inventario.model.Stock;
import com.proyecto_frutos_velasquez.inventario.repository.MovStockRepository;
import com.proyecto_frutos_velasquez.inventario.repository.StockRepository;
import com.proyecto_frutos_velasquez.inventario.service.MovimientoStockService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MovimientoStockServiceTest {

    @Mock
    private MovStockRepository movRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private MovimientoStockService movStockService;

    @Test
    void testGuardarMovimiento_Entrada_Exito() {
        // Arrange
        MovimientoStock mov = new MovimientoStock();
        mov.setIdProducto(100L);
        mov.setTipoMovimiento("ENTRADA");
        mov.setCantidad(20.0);

        Stock stockActual = new Stock();
        stockActual.setIdProducto(100L);
        stockActual.setStockActual(30.0);

        MovimientoStock movGuardado = new MovimientoStock();
        movGuardado.setIdMovimiento(1L);
        movGuardado.setTipoMovimiento("ENTRADA");
        movGuardado.setCantidad(20.0);

        when(stockRepository.findByIdProducto(100L)).thenReturn(Optional.of(stockActual));
        when(movRepository.save(any(MovimientoStock.class))).thenReturn(movGuardado);

        // Act
        MovimientoStock resultado = movStockService.guardarMovimiento(mov);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdMovimiento());
        assertEquals(50.0, stockActual.getStockActual());
        verify(stockRepository, times(1)).save(stockActual);
    }
}