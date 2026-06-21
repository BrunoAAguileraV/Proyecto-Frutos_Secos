package com.proyecto_frutos_velasquez.payment_service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto_frutos_velasquez.payment_service.model.Pago;
import com.proyecto_frutos_velasquez.payment_service.repository.PagoRepository;
import com.proyecto_frutos_velasquez.payment_service.service.PagoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void testProcesarPago_MontoMayorACero_Aprobado() {
        // Arrange
        Pago pagoEntrada = new Pago();
        pagoEntrada.setVentaId(10L);
        pagoEntrada.setMonto(25000.0);
        pagoEntrada.setMetodoPago("TARJETA");

        // Simulamos que el repositorio devuelve exactamente lo que le pasamos
        when(pagoRepository.save(any(Pago.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Pago resultado = pagoService.procesarPago(pagoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstadoPago(), "El pago debería estar aprobado porque el monto es mayor a 0");
        assertNotNull(resultado.getTransaccionId(), "El ID de transacción no debería ser nulo");
        assertNotNull(resultado.getFechaPago(), "La fecha de pago no debería ser nula");
        
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }
}