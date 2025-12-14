package com.apisSB.ApisRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apisSB.ApisRest.entity.Categoria;

@Service
public interface CategoriaService {

    Categoria registrarCategoria(Categoria categoria);
    List <Categoria> listarCategorias();
    Optional<Categoria> obtenerCategoriaPorId(Long idCategoria);
    Optional<Categoria> actualizarCategoria(Long idCategoria, String nombreCategoria);
    void eliminarCategoria(Long idCategoria);
    
}
