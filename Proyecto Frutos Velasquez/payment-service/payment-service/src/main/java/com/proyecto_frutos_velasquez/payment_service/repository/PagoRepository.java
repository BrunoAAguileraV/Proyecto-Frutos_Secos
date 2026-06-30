package com.proyecto_frutos_velasquez.payment_service.repository;

import com.proyecto_frutos_velasquez.payment_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findByVentaId(Long ventaId);
}
