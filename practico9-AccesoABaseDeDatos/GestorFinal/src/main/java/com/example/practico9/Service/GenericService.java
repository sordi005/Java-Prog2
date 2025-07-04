package com.example.practico9.Service;

import java.sql.SQLException;
import java.util.List;

public interface GenericService<T>{
    void crear(T entidad)throws SQLException;
    T leer(Long id)throws SQLException;
    List<T>listar()throws SQLException;
    void actualizar(T entidad)throws SQLException;
    void eliminar(Long id) throws SQLException;
}
