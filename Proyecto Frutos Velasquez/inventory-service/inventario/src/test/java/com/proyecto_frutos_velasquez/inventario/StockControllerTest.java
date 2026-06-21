package com.proyecto_frutos_velasquez.inventario;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto_frutos_velasquez.inventario.controller.StockController;
import com.proyecto_frutos_velasquez.inventario.model.Stock;
import com.proyecto_frutos_velasquez.inventario.service.StockService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    void testBuscarPorProducto_Exito() throws Exception {
        // Arrange
        Stock mockStock = new Stock();
        mockStock.setIdStock(1L);
        mockStock.setIdProducto(100L);
        mockStock.setStockActual(50.0);

        when(stockService.buscarPorProducto(100L)).thenReturn(Optional.of(mockStock));

        // Act & Assert
        mockMvc.perform(get("/api/v1/inventario/producto/100"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.stockActual").value(50.0));
    }
}