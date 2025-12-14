package com.apisSB.ApisRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisSB.ApisRest.entity.EstadoProducto;
import com.apisSB.ApisRest.entity.Producto;
import com.apisSB.ApisRest.service.ProductoService;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registrarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
    
    @GetMapping("/listar")
    public ResponseEntity <List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
    
    @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idProducto) {
        Optional<Producto> producto = productoService.buscarPorId(idProducto);
        return producto.isPresent() ? ResponseEntity.ok(producto.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto) {
        try{
            Producto productoActualizado = new Producto();
            productoActualizado.setIdProducto(idProducto);
            productoActualizado.setCantidad(producto.getCantidad());
            productoActualizado.setDescripcion(producto.getDescripcion());
            productoActualizado.setEstadoProducto(producto.getEstadoProducto());
            productoActualizado.setNombreProducto(producto.getNombreProducto());
            productoActualizado.setPrecio(producto.getPrecio());
            
            Producto actualizado = productoService.actualizarProducto(idProducto, productoActualizado);
            return ResponseEntity.ok(actualizado);
        }
        
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto) {
        try{
            productoService.eliminarProducto(idProducto);
            return ResponseEntity.ok("Producto eliminado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @PutMapping("/cambiar-estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto, @RequestBody String estadoProducto) {
        try{
            Producto productoActualizado = productoService.cambiarEstadoProducto(idProducto, estadoProducto);
            return ResponseEntity.ok(productoActualizado);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Producto>> listarProductosPorEstado(@PathVariable EstadoProducto estado) {
        List<Producto> productos = productoService.listarProductosPorEstado(estado);
            return ResponseEntity.ok(productos);
    }
}
