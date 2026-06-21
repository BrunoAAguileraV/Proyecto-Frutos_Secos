package com.proyecto_frutos_velasquez.inventario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto_frutos_velasquez.inventario.controller.MovStockController;
import com.proyecto_frutos_velasquez.inventario.model.MovimientoStock;
import com.proyecto_frutos_velasquez.inventario.service.MovimientoStockService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MovStockController.class)
public class MovStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientoStockService movStockService;

    @Test
    void testRegistrarMovimiento_Exito() throws Exception {
        // Arrange
        MovimientoStock movGuardado = new MovimientoStock();
        movGuardado.setIdMovimiento(1L);
        movGuardado.setTipoMovimiento("ENTRADA");
        movGuardado.setCantidad(20.0);

        when(movStockService.guardarMovimiento(any(MovimientoStock.class))).thenReturn(movGuardado);

        // Act & Assert
        String jsonBody = "{\"idProducto\": 100, \"tipoMovimiento\": \"ENTRADA\", \"cantidad\": 20.0}";
        
        mockMvc.perform(post("/api/v1/inventario/movimientos")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.idMovimiento").value(1))
               .andExpect(jsonPath("$.tipoMovimiento").value("ENTRADA"));
    }
}