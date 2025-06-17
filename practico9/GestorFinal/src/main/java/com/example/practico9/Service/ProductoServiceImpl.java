package com.example.practico9.Service;

import com.example.practico9.dao.GenericDao;
import com.example.practico9.dao.CategoriaDAOImpl;
import com.example.practico9.dao.ProductoDAOImpl;
import com.example.practico9.model.Categoria;
import com.example.practico9.model.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoServiceImpl implements GenericService<Producto> {
    private final GenericDao<Producto> productoDao;
    //Se le pasa categoriaDao ya que se van a hacer validaciones de categoriaExistente
    private final GenericDao<Categoria> categoriaDao;

    public ProductoServiceImpl(ProductoDAOImpl productoDao, CategoriaDAOImpl categoriaDao) {
        this.productoDao = productoDao;
        this.categoriaDao = categoriaDao;
    }

    @Override
    public void crear(Producto producto) throws SQLException {
        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException(" -- Nombre vacío.");
        }
        /*if(((ProductoDAOImpl)productoDao).existeNombre(producto.getNombre())){
            throw new IllegalArgumentException("El nombre del producto ya existe");
        }*/
        if(producto.getPrecio() == null || producto.getPrecio() < 0){
            throw new IllegalArgumentException(" -- Precio Debe ser Válido y Obligatorio.");
        }
        if(producto.getCantidad() < 0){
            throw new IllegalArgumentException(" -- Cantidad Debe ser Válida y Obligatoria.");
        }
        if (producto.getCategoria()==null || !bucscarCategoriaById(producto.getCategoria().getId()) ){
            throw new IllegalArgumentException(" -- Categoria No Existente.");
        }
        productoDao.crear(producto);
    }
    @Override
    public Producto leer(Long id) throws SQLException {
        return productoDao.leer(id);
    }
    @Override
    public void actualizar(Producto producto) throws SQLException {
        if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException(" -- Nombre vacío.");
        }
        if(producto.getPrecio() == null || producto.getPrecio() < 0){
            throw new IllegalArgumentException(" -- Precio Debe ser Válido y Obligatorio.");
        }
        if(producto.getCantidad() < 0){
            throw new IllegalArgumentException(" -- Cantidad Debe ser Válida y Obligatoria.");
        }
        if (producto.getCategoria()==null || !bucscarCategoriaById(producto.getCategoria().getId())){
            throw new IllegalArgumentException(" -- Categoria  Obligatoria.");
        }
        productoDao.crear(producto);
    }
    @Override
    public void eliminar(Long id) throws SQLException {
        productoDao.eliminar(id);
    }
    @Override
    public List<Producto> listar() throws SQLException {
        return productoDao.listar();
    }
    public boolean bucscarCategoriaById(Long id)throws SQLException{
        Categoria categoriaExistente= categoriaDao.leer(id);
        if (categoriaExistente == null) {
            return false;
        }
        return true;
    }

}

