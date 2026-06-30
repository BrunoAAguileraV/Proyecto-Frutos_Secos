package com.frutos.service_promo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.frutos.service_promo.controller.PromoController;
import com.frutos.service_promo.model.Promo;
import com.frutos.service_promo.service.PromoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

@WebMvcTest(PromoController.class)
public class PromoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromoService promoService;

    @Test
    void testObtenerPorCodigo_Exito() throws Exception {
        // Arrange
        Promo mockPromo = new Promo();
        mockPromo.setId(1L);
        mockPromo.setCodigo("MANI20");
        mockPromo.setPorcentajeDescuento(20);
        mockPromo.setActiva(true);

        when(promoService.buscarPorCodigo("MANI20")).thenReturn(Optional.of(mockPromo));

        // Act & Assert
        mockMvc.perform(get("/api/v1/promos/MANI20"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.codigo").value("MANI20"))
               .andExpect(jsonPath("$.porcentajeDescuento").value(20))
               .andExpect(jsonPath("$.activa").value(true));
    }
}