package com.proyecto_frutos_velasquez.analiticas.service;

import com.proyecto_frutos_velasquez.analiticas.model.ResumenDiario;
import com.proyecto_frutos_velasquez.analiticas.repository.AnaliticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.time.LocalDate;

@Service
public class AnaliticaService {
    
    @Autowired
    private AnaliticaRepository analiticaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResumenDiario generarReporteDiario(LocalDate fecha) {
        Double ingresos = 0.0;

        // 1. Consultamos al micro de Ventas (8083) con escudo protector (try-catch)
        try {
            ingresos = webClientBuilder.build().get()
                .uri("http://localhost:8081/api/v1/ventas/total-dia/" + fecha)
                .retrieve()
                .bodyToMono(Double.class)
                .block();
                
        } catch (WebClientResponseException.NotFound e) {
            // Si Ventas responde 404 (No hay ventas en esa fecha)
            System.out.println("No se encontraron ventas para la fecha: " + fecha);
            ingresos = 0.0;
        } catch (Exception e) {
            // Si el micro de Ventas está apagado o falla por otra razón
            System.out.println("Error de conexión con microservicio de Ventas: " + e.getMessage());
            ingresos = 0.0; 
        }
        Double costos = 0.0; 

        // 3. Armamos el reporte
        ResumenDiario reporte = new ResumenDiario();
        reporte.setFecha(fecha);
        reporte.setIngresosTotales(ingresos != null ? ingresos : 0.0);
        reporte.setCostosTotales(costos);
        reporte.setUtilidadNeta(reporte.getIngresosTotales() - reporte.getCostosTotales());

        // 4. Guardamos en BD y retornamos
        return analiticaRepository.save(reporte);
    }
}
