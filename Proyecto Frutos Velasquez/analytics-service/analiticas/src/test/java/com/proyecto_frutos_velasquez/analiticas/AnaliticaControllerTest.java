package com.proyecto_frutos_velasquez.analiticas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.proyecto_frutos_velasquez.analiticas.controller.AnaliticaController;
import com.proyecto_frutos_velasquez.analiticas.model.ResumenDiario;
import com.proyecto_frutos_velasquez.analiticas.service.AnaliticaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

@WebMvcTest(AnaliticaController.class)
public class AnaliticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnaliticaService analiticaService;

    @Test
    void testObtenerReporte_Exito() throws Exception {
        // Arrange
        ResumenDiario reporteMock = new ResumenDiario();
        reporteMock.setIdReporte(1L);
        reporteMock.setFecha(LocalDate.of(2026, 6, 21));
        reporteMock.setIngresosTotales(150000.0);
        reporteMock.setCostosTotales(0.0);
        reporteMock.setUtilidadNeta(150000.0);

        when(analiticaService.generarReporteDiario(any(LocalDate.class))).thenReturn(reporteMock);

        // Act & Assert
        // Usamos .param() porque en el controller pusiste @RequestParam
        mockMvc.perform(get("/api/v1/analitica/reporte")
               .param("fecha", "2026-06-21")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.ingresosTotales").value(150000.0))
               .andExpect(jsonPath("$.utilidadNeta").value(150000.0));
    }
}