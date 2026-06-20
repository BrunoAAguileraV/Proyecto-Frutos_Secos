package com.proyecto_frutos_velasquez.analiticas.repository;

import com.proyecto_frutos_velasquez.analiticas.model.ResumenDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliticaRepository extends JpaRepository<ResumenDiario, Long> {
}
