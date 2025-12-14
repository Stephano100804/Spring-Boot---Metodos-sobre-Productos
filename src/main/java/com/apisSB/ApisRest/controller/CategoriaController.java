package com.apisSB.ApisRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisSB.ApisRest.entity.Categoria;
import com.apisSB.ApisRest.service.CategoriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/apis/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/registrar")
    public ResponseEntity<Categoria> registrarCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.registrarCategoria(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/buscar/{idCategoria}")
    public ResponseEntity<?> listarCategoriaPorId(@PathVariable Long idCategoria) {
        
        Optional<Categoria> cate = categoriaService.obtenerCategoriaPorId(idCategoria);

        return cate.isPresent() ? ResponseEntity.ok(cate.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
    
    @PutMapping("actualizar/{idCategoria}")
    public ResponseEntity<?> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria nombreCategoria) {
        try{
            Categoria catUpdate = new Categoria();
            catUpdate.setIdCategoria(idCategoria);
            catUpdate.setNombreCategoria(nombreCategoria.getNombreCategoria());
            Categoria catActualizado = categoriaService.actualizarCategoria(idCategoria, nombreCategoria.getNombreCategoria()).get();
            return ResponseEntity.ok(catActualizado);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }
    @DeleteMapping("/eliminar/{idCategoria}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long idCategoria) {
        try{
            categoriaService.eliminarCategoria(idCategoria);
            return ResponseEntity.ok("Categoria eliminada");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }
}
