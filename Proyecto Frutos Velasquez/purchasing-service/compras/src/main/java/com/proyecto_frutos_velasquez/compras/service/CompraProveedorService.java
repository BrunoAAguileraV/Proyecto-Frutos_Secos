package com.proyecto_frutos_velasquez.compras.service;

import com.proyecto_frutos_velasquez.compras.model.CompraProveedor;
import com.proyecto_frutos_velasquez.compras.repository.CompraProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraProveedorService {

    @Autowired
    private CompraProveedorRepository compraRepository;

    public List<CompraProveedor> listarTodas() {
        return compraRepository.findAll();
    }

    public Optional<CompraProveedor> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public List<CompraProveedor> buscarPorProducto(Long idProducto) {
        return compraRepository.findByIdProducto(idProducto);
    }

    public CompraProveedor registrarCompra(CompraProveedor compra) {
        // Asignamos la fecha de hoy si no viene especificada
        if (compra.getFechaCompra() == null) {
            compra.setFechaCompra(LocalDate.now());
        }
        return compraRepository.save(compra);
    }
}
