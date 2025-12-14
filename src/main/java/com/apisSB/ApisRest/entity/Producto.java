package com.apisSB.ApisRest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue
    @Column(name="id_producto")
    private Long idProducto;
    
    @Column(name="nombre_producto", nullable=false, length=250)
    private String nombreProducto;

    @Column(name="descripcion", nullable=false, length=500)
    private String descripcion;

    @Column(name="cantidad", nullable=false)
    private int cantidad;

    @Column(name="precio", nullable=false)
    private double precio;

    @Enumerated(EnumType.STRING)
    @Column(name="estado_producto", nullable=false)
    private EstadoProducto estadoProducto;

    @ManyToOne
    @JoinColumn(name="id_categoria",referencedColumnName = "id_categoria")
    private Categoria categoria;
}
