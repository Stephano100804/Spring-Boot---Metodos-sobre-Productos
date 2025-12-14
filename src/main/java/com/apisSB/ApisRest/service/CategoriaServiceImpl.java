package com.apisSB.ApisRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisSB.ApisRest.entity.Categoria;
import com.apisSB.ApisRest.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Categoria registrarCategoria(Categoria categoria) {
        if(categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
            throw new IllegalArgumentException("La categoria ya existe");
        } else {
            return categoriaRepository.save(categoria);
        }
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    @Override
    public Optional<Categoria> actualizarCategoria(Long idCategoria, String nombreCategoria) {
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoriaExistente.setNombreCategoria(nombreCategoria);
        categoriaRepository.save(categoriaExistente);
        return Optional.of(categoriaExistente);
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoriaRepository.deleteById(idCategoria);
    }
    
}
