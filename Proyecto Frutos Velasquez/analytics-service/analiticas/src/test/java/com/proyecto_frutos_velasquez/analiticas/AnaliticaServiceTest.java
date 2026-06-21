package com.proyecto_frutos_velasquez.analiticas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto_frutos_velasquez.analiticas.model.ResumenDiario;
import com.proyecto_frutos_velasquez.analiticas.repository.AnaliticaRepository;
import com.proyecto_frutos_velasquez.analiticas.service.AnaliticaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class AnaliticaServiceTest {

    @Mock
    private AnaliticaRepository analiticaRepository;

    @Mock
    private WebClient.Builder webClientBuilder; // Lo mockeamos para que la inyección no falle

    @InjectMocks
    private AnaliticaService analiticaService;

    @Test
    void testGenerarReporteDiario_FalloVentas_GuardaReporteEnCero() {
        // Arrange
        LocalDate fechaTest = LocalDate.of(2026, 6, 21);
        ResumenDiario reporteGuardado = new ResumenDiario(1L, fechaTest, 0.0, 0.0, 0.0);

        // Simulamos que al intentar guardar, la base de datos responde bien
        when(analiticaRepository.save(any(ResumenDiario.class))).thenReturn(reporteGuardado);

        // Act
        // Al ejecutar, el WebClient simulado fallará, tu try-catch lo atrapará y pondrá ingresos en 0.0
        ResumenDiario resultado = analiticaService.generarReporteDiario(fechaTest);

        // Assert
        assertNotNull(resultado);
        assertEquals(0.0, resultado.getIngresosTotales(), "El reporte debe asumir 0 ingresos si Ventas falla");
        assertEquals(0.0, resultado.getUtilidadNeta());
        
        // Verificamos que sí o sí se intentó guardar en la base de datos
        verify(analiticaRepository, times(1)).save(any(ResumenDiario.class));
    }
}