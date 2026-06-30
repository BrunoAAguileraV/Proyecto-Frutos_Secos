package com.frutos.service_customer.service;

import com.frutos.service_customer.model.Customer;
import com.frutos.service_customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> listarTodos() {
        return customerRepository.findAll();
    }

    public Optional<Customer> buscarPorRut(String rut) {
        return customerRepository.findByRut(rut);
    }

    public Optional<Customer> buscarPorUsuarioId(Long usuarioId) {
        return customerRepository.findByUsuarioId(usuarioId);
    }

    public Customer guardarOActualizar(Customer customer) {
        // Validación estricta: Si es un registro nuevo, evitamos duplicar RUT o Usuario
        if (customer.getId() == null) {
            if (customerRepository.findByRut(customer.getRut()).isPresent()) {
                throw new RuntimeException("Ya existe un cliente registrado con el RUT: " + customer.getRut());
            }
            if (customerRepository.findByUsuarioId(customer.getUsuarioId()).isPresent()) {
                throw new RuntimeException("Este usuario ya tiene un perfil de cliente asignado.");
            }
        }
        return customerRepository.save(customer);
    }

    public void eliminarCliente(Long id) {
        customerRepository.deleteById(id);
    }
}