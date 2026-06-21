package com.proyecto_frutos_secos.shipping_service.repository;

import com.proyecto_frutos_secos.shipping_service.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
    Optional<Despacho> findByVentaId(Long ventaId);
}
