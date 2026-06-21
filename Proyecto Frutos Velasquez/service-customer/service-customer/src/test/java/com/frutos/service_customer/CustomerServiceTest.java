package com.frutos.service_customer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.frutos.service_customer.model.Customer;
import com.frutos.service_customer.repository.CustomerRepository;
import com.frutos.service_customer.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testBuscarPorRut_Exito() {
        // Arrange
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1L);
        mockCustomer.setRut("12345678-9");
        mockCustomer.setNombre("Oliver Vidal");
        
        // Le decimos al mock que cuando busquen este RUT, devuelva a Oliver
        when(customerRepository.findByRut("12345678-9")).thenReturn(Optional.of(mockCustomer));

        // Act
        // Usamos el método real que sí existe en tu clase
        Optional<Customer> resultado = customerService.buscarPorRut("12345678-9");

        // Assert
        assertTrue(resultado.isPresent(), "El cliente debería ser encontrado");
        assertEquals("Oliver Vidal", resultado.get().getNombre());
    }
}