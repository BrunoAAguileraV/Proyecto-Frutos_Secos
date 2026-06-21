package com.frutos.service_promo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.frutos.service_promo.model.Promo;
import com.frutos.service_promo.repository.PromoRepository;
import com.frutos.service_promo.service.PromoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PromoServiceTest {

    @Mock
    private PromoRepository promoRepository;

    @InjectMocks
    private PromoService promoService;

    @Test
    void testBuscarPorCodigo_TransformaMayuscula_Exito() {
        // Arrange
        Promo mockPromo = new Promo();
        mockPromo.setId(1L);
        mockPromo.setCodigo("MANI20");
        mockPromo.setPorcentajeDescuento(20);
        mockPromo.setActiva(true);

        // Le decimos al mock que espere la llamada en MAYÚSCULAS
        when(promoRepository.findByCodigo("MANI20")).thenReturn(Optional.of(mockPromo));

        // Act
        // Le mandamos el código en minúsculas para probar tu lógica
        Optional<Promo> resultado = promoService.buscarPorCodigo("mani20");

        // Assert
        assertTrue(resultado.isPresent(), "Debe encontrar la promoción");
        assertEquals("MANI20", resultado.get().getCodigo());
        assertEquals(20, resultado.get().getPorcentajeDescuento());
    }
}