package com.apisSB.ApisRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apisSB.ApisRest.entity.EstadoProducto;
import com.apisSB.ApisRest.entity.Producto;

@Service
public interface ProductoService {
    Producto registrarProducto(Producto producto);
    List<Producto> listarProductos();
    Optional<Producto> buscarPorNombre(String nombre);
    Optional<Producto> buscarPorId(Long idProducto);
    Producto actualizarProducto(Long idProducto,Producto producto);
    void eliminarProducto(Long idProducto);
    Producto cambiarEstadoProducto(Long idProducto, String estadoProducto);
    List<Producto> listarProductosPorEstado(EstadoProducto estadoProducto);
}
