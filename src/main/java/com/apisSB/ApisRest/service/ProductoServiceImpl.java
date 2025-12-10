package com.apisSB.ApisRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apisSB.ApisRest.entity.EstadoProducto;
import com.apisSB.ApisRest.entity.Producto;
import com.apisSB.ApisRest.repository.ProductoRepository;
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProducto(nombre);
    }

    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        return productoRepository.findById(idProducto);
    }

    @Override
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto proExis = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        proExis.setNombreProducto(producto.getNombreProducto());
        proExis.setNombreProducto(producto.getDescripcion());
        proExis.setPrecio(producto.getPrecio());
        proExis.setCantidad(producto.getCantidad());
        proExis.setEstadoProducto(producto.getEstadoProducto());
        
        return productoRepository.save(proExis);

    }

    @Override
    public void eliminarProducto(Long idProducto) {
         productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto cambiarEstadoProducto(Long idProducto, String estadoProducto) {
        Producto proExis = productoRepository.findById(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        proExis.setEstadoProducto(EstadoProducto.valueOf(estadoProducto));
        return productoRepository.save(proExis);
    }

    @Override
    public List<Producto> listarProductosPorEstado(EstadoProducto estadoProducto) {
        return productoRepository.findByEstadoProducto(estadoProducto);
    }
    
}
