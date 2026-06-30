package com.frutos.service_customer;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.frutos.service_customer.controller.CustomerController;
import com.frutos.service_customer.model.Customer;
import com.frutos.service_customer.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void testObtenerPorRut_Exito() throws Exception {
        // Arrange
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1L);
        mockCustomer.setRut("12345678-9");
        mockCustomer.setNombre("Oliver Vidal");
        
        // Le decimos a Mockito que el servicio ahora devuelve un Optional, igual que en tu código real
        when(customerService.buscarPorRut("12345678-9")).thenReturn(Optional.of(mockCustomer));

        // Act & Assert
        // Le pegamos a la ruta exacta que definiste en el RequestMapping y GetMapping
        mockMvc.perform(get("/api/v1/customers/rut/12345678-9"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombre").value("Oliver Vidal"))
               .andExpect(jsonPath("$.rut").value("12345678-9"));
    }
}