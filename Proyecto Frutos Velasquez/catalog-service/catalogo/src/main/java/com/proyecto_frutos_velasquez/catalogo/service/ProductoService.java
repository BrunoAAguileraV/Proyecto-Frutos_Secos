package com.proyecto_frutos_velasquez.catalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_frutos_velasquez.catalogo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import com.proyecto_frutos_velasquez.catalogo.model.Producto;


@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodo(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }

    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> buscarPorCategoria(Long id_Categoria){
        return productoRepository.findByCategoriaId(id_Categoria);
    }

    @Transactional
    public void Eliminar(Long id){
        productoRepository.deleteById(id);
    }

}
