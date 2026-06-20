package com.proyecto_frutos_velasquez.ventas.service;

import com.proyecto_frutos_velasquez.ventas.model.*;
import com.proyecto_frutos_velasquez.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional //si un producto falla, se cancela TODA la venta
    public Venta procesarVenta(Venta venta) {
        double totalVenta = 0;

        for (DetalleVenta detalle : venta.getDetalles()) {
            try {
                Integer precio = webClientBuilder.build().get()
                    .uri("http://localhost:8081/api/v1/catalogo/productos/precio/" + detalle.getIdProducto())
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
                if (precio == null || precio <= 0) {
                    throw new RuntimeException("Error: El producto ID " + detalle.getIdProducto() + " tiene precio 0 o no válido en el catálogo.");
                }

                detalle.setSubtotal(precio * detalle.getCantidad());
                totalVenta += detalle.getSubtotal();
                
                detalle.setVenta(venta);
            } catch (WebClientResponseException.NotFound e) {
                throw new RuntimeException("Venta cancelada: El producto ID " + detalle.getIdProducto() + " no existe en el Catálogo.");
            } catch (Exception e) {
                // Si el microservicio de Catálogo está apagado o falla
                throw new RuntimeException("Error de conexión con el Catálogo: " + e.getMessage());
            }
        }

        venta.setTotal(totalVenta);
        venta.setFechaHora(LocalDateTime.now());
        return ventaRepository.save(venta);
    }

    public Double obtenerTotalVentasDia(LocalDate fecha) {
        // Este método busca desde las 00:00:00 hasta las 23:59:59 del día solicitado
        return ventaRepository.sumarTotalPorRango(fecha.atStartOfDay(), fecha.atTime(LocalTime.MAX));
    }
}
