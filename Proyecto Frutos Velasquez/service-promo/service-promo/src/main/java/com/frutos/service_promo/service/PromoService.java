package com.frutos.service_promo.service;

import com.frutos.service_promo.model.Promo;
import com.frutos.service_promo.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    public List<Promo> listarTodas() {
        return promoRepository.findAll();
    }

    public Optional<Promo> buscarPorCodigo(String codigo) {
        return promoRepository.findByCodigo(codigo.toUpperCase());
    }

    public Promo guardar(Promo promo) {
        promo.setCodigo(promo.getCodigo().toUpperCase());
        return promoRepository.save(promo);
    }
    
    public void eliminarPromocion(Long id) {
        promoRepository.deleteById(id);
    }
}