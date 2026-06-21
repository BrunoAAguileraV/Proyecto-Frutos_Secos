package com.proyecto_frutos_velasquez.payment_service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto_frutos_velasquez.payment_service.controller.PagoController;
import com.proyecto_frutos_velasquez.payment_service.model.Pago;
import com.proyecto_frutos_velasquez.payment_service.service.PagoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PagoController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @Test
    void testProcesar_Exito() throws Exception {
        // Arrange
        Pago pagoSalida = new Pago();
        pagoSalida.setId(1L);
        pagoSalida.setVentaId(10L);
        pagoSalida.setMonto(25000.0);
        pagoSalida.setEstadoPago("APROBADO");
        pagoSalida.setTransaccionId("A1B2C3D4E5F6"); // Token falso

        when(pagoService.procesarPago(any(Pago.class))).thenReturn(pagoSalida);

        // Act & Assert
        String jsonBody = "{\"ventaId\": 10, \"monto\": 25000.0, \"metodoPago\": \"TARJETA\"}";

        mockMvc.perform(post("/api/v1/payments/procesar")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBody))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.estadoPago").value("APROBADO"))
               .andExpect(jsonPath("$.transaccionId").value("A1B2C3D4E5F6"));
    }
}