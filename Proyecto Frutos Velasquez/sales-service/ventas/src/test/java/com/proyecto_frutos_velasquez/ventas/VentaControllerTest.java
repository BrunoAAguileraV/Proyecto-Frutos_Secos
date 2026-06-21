package com.proyecto_frutos_velasquez.ventas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto_frutos_velasquez.ventas.controller.VentaController;
import com.proyecto_frutos_velasquez.ventas.model.Venta;
import com.proyecto_frutos_velasquez.ventas.service.VentaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @Test
    void testCrearVenta_Exito() throws Exception {
        // Arrange
        Venta ventaSimulada = new Venta();
        ventaSimulada.setIdVenta(1L);
        ventaSimulada.setTotal(25000.0);
        ventaSimulada.setMedioPago("Efectivo");

        when(ventaService.procesarVenta(any(Venta.class))).thenReturn(ventaSimulada);

        // Act & Assert
        String jsonBody = "{\"medioPago\": \"Efectivo\", \"detalles\": []}";

        mockMvc.perform(post("/api/v1/ventas")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.idVenta").value(1))
               .andExpect(jsonPath("$.total").value(25000.0));
    }

    @Test
    void testObtenerTotalDia_Exito() throws Exception {
        // Arrange
        when(ventaService.obtenerTotalVentasDia(any(LocalDate.class))).thenReturn(50000.0);

        // Act & Assert
        mockMvc.perform(get("/api/v1/ventas/total-dia/2026-06-21"))
               .andExpect(status().isOk())
               .andExpect(content().string("50000.0"));
    }
}