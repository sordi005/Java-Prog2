package com.example.practico9.Service;

import com.example.practico9.dao.CategoriaDAOImpl;
import com.example.practico9.dao.GenericDao;
import com.example.practico9.model.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaServiceImpl implements GenericService<Categoria> {
    private final GenericDao<Categoria> categoriaDao ;

    public CategoriaServiceImpl(CategoriaDAOImpl categoriaDao) {
        this.categoriaDao = categoriaDao;
    }
    @Override
    public void crear(Categoria categoria) throws SQLException {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        //if (((CategoriaDAOImpl) categoriaDao).existeNombre(categoria.getNombre())) {
         //   throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        //}
        categoriaDao.crear(categoria);
    }
    @Override
    public Categoria leer(Long id) throws SQLException {
        return categoriaDao.leer(id);
    }
    @Override
    public void actualizar(Categoria categoria) throws SQLException {
        if (categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        categoriaDao.actualizar(categoria);
    }
    @Override
    public void eliminar(Long id) throws SQLException {
        categoriaDao.eliminar(id);
    }
    @Override
    public List<Categoria> listar() throws SQLException {
        return categoriaDao.listar();
    }

    }
