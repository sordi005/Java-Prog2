package com.example.practico9.dao;

import com.example.practico9.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class CategoriaDAOImpl implements GenericDao<Categoria> {
    private final Connection conexion;

    public CategoriaDAOImpl(Connection conexion){
        this.conexion = conexion;
    }
    public void crearTabla()throws SQLException{
        String sql = """
                 CREATE TABLE IF NOT EXISTS categoria (
                 id INT AUTO_INCREMENT PRIMARY KEY,
                 nombre VARCHAR(100) NOT NULL,
                 descripcion TEXT
             );
            """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'categoria' creada o ya existe.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla categoria: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void crear(Categoria categoria)throws SQLException {
        String sqlInsert = "INSERT INTO categoria (nombre,descripcion) VALUES (?,?)";
        try(PreparedStatement ps = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,categoria.getNombre());
            ps.setString(2,categoria.getDescripcion());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo crear categoria");
            }
            try(ResultSet rsId = ps.getGeneratedKeys()){
                if(rsId.next()){
                    categoria.setId(rsId.getLong(1));
                }
            }
        }
    }
    @Override
    public Categoria leer(Long id )throws SQLException{
        String sqlSelect = "SELECT * FROM categoria WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    return mapearCategoria(rs);
                }else {
                    return  null;
                }
            }
        }
    }
    @Override
    public void actualizar(Categoria categoria) throws SQLException{
        String sqlUpdate = "UPDATE categoria SET nombre=?,descripcion=? WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlUpdate)){
            ps.setString(1,categoria.getNombre());
            ps.setString(2,categoria.getDescripcion());
            ps.setLong(3,categoria.getId()); ;
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo actualizar categoria");
            }
        }
    }
    @Override
    public void eliminar(Long id)throws SQLException{
        String sqlDelete = " DELETE FROM categoria WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlDelete)){
            ps.setLong(1,id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo eliminar categoria");
            }
        }
    }
    @Override
    public List<Categoria> listar() throws SQLException{
        List<Categoria> categorias = new ArrayList<>();
        String sqlSelect = "SELECT * FROM categoria";
        try(Statement ps=conexion.createStatement() ){
            try(ResultSet rs=ps.executeQuery(sqlSelect)){
                while (rs.next()){
                    categorias.add(mapearCategoria(rs));
                }
            }
            return categorias;
        }
    }
    public boolean existeNombre(String nombre)throws SQLException{
        String sqlSelect = "SELECT COUNT(*) FROM categoria WHERE nombre = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)) {
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
            return false;
        }
    }
    public Categoria mapearCategoria(ResultSet rs)throws SQLException{
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("id"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setDescripcion(rs.getString("descripcion"));
            return categoria;
    }
}
