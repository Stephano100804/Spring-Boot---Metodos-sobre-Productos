package com.apisSB.ApisRest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apisSB.ApisRest.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombreProducto(String nombreProducto);
    Optional<Producto> findById(Long idProducto);
    List<Producto> findByEstadoProducto(String estadoProducto);
}
