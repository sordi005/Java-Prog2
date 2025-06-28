package com.example.practico9.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Producto> productos;

    public Categoria(){}
    public Categoria( String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void addProducto(Producto producto) {
        if(productos == null) {
            productos = new ArrayList<>();
        }
        if(producto!=null){
            productos.add(producto);
        }
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
