package com.proyecto_frutos_velasquez.payment_service.service;

import com.proyecto_frutos_velasquez.payment_service.model.Pago;
import com.proyecto_frutos_velasquez.payment_service.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;

    public Pago procesarPago(Pago pago) {
        pago.setFechaPago(LocalDateTime.now());
        pago.setTransaccionId(UUID.randomUUID().toString().substring(0, 12).toUpperCase());
        
        // Simulación comercial: si el monto es mayor a 0, se aprueba automáticamente
        if (pago.getMonto() > 0) {
            pago.setEstadoPago("APROBADO");
        } else {
            pago.setEstadoPago("RECHAZADO");
        }
        return pagoRepository.save(pago);
    }
    public void eliminarPago(Long id) {
        pagoRepository.deleteById(id);
    }
}
