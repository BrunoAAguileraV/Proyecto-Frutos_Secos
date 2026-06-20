package com.proyecto_frutos_velasquez.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto_frutos_velasquez.catalogo.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.proyecto_frutos_velasquez.catalogo.model.Categoria;

@RestController
@RequestMapping("/api/v1/catalogo/categoria")
@CrossOrigin(origins = "*")
@Tag(name = "Categorias", description = "Endpoints para gestionar las categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    private List<Categoria> listarTodas(){
        return categoriaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtener(@PathVariable Long id){
        return categoriaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){
        try{
            Categoria nueva = categoriaService.guardar(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (categoriaService.buscarPorId(id).isPresent()){
            categoriaService.Eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}