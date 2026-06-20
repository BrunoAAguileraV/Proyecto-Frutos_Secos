package com.frutos.service_customer.repository;

import com.frutos.service_customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByRut(String rut);
    Optional<Customer> findByUsuarioId(Long usuarioId);
}