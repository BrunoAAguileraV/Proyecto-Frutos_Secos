package com.proyecto_frutos_velasquez.ventas;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto_frutos_velasquez.ventas.repository.VentaRepository;
import com.proyecto_frutos_velasquez.ventas.service.VentaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    @Test
    void testObtenerTotalVentasDia_Exito() {
        // Arrange
        LocalDate fechaTest = LocalDate.of(2026, 6, 21);
        
        // Simulamos que la base de datos nos devuelve 50 lucas para ese día
        when(ventaRepository.sumarTotalPorRango(any(), any())).thenReturn(50000.0);

        // Act
        Double resultado = ventaService.obtenerTotalVentasDia(fechaTest);

        // Assert
        assertNotNull(resultado);
        assertEquals(50000.0, resultado, "El total del día debería coincidir con la sumatoria de la base de datos");
        
        // Verificamos que efectivamente se llamó al repositorio 1 vez
        verify(ventaRepository, times(1)).sumarTotalPorRango(any(), any());
    }
}