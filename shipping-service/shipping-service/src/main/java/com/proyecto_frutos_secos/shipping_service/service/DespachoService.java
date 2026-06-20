package com.proyecto_frutos_secos.shipping_service.service;

import com.proyecto_frutos_secos.shipping_service.model.Despacho;
import com.proyecto_frutos_secos.shipping_service.repository.DespachoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DespachoService {
    private final DespachoRepository despachoRepository;

    public Despacho registrarDespacho(Despacho despacho) {
        despacho.setEstadoDespacho("PREPARACION");
        despacho.setFechaDespacho(LocalDateTime.now());
        despacho.setNumeroSeguimiento("FV-" + new Random().nextInt(999999));
        return despachoRepository.save(despacho);
    }

    public Despacho actualizarEstado(Long id, String nuevoEstado) {
        Despacho d = despachoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho no encontrado"));
        d.setEstadoDespacho(nuevoEstado);
        return despachoRepository.save(d);
    }
}
