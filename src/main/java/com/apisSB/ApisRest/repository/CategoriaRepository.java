package com.apisSB.ApisRest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apisSB.ApisRest.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
    boolean existsByNombreCategoria(String nombreCategoria);

}
