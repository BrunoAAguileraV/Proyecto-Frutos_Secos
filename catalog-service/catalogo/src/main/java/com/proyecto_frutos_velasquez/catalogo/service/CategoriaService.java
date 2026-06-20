package com.proyecto_frutos_velasquez.catalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_frutos_velasquez.catalogo.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

import com.proyecto_frutos_velasquez.catalogo.model.Categoria;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas(){
        return categoriaRepository.findAll();
    }

    public Categoria guardar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Long id){
        return categoriaRepository.findById(id);
    }

    @Transactional
    public void Eliminar(Long id){
        categoriaRepository.deleteById(id);
    }

}
