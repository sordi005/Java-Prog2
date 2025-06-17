package com.example.practico9.dao;

import com.example.practico9.DbConection;
import com.example.practico9.model.Categoria;
import com.example.practico9.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements GenericDao<Producto> {
    private final Connection conexion;

    public ProductoDAOImpl(Connection conexion){
        this.conexion = conexion;
    }
    public void crearTabla()throws SQLException{
            String sql = """
            CREATE TABLE IF NOT EXISTS producto (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                descripcion TEXT,
                precio DECIMAL(10,2) NOT NULL,
                cantidad INT NOT NULL,
                categoria_id INT NOT NULL,
                FOREIGN KEY (categoria_id) REFERENCES categoria(id)
            )
            """;
            try (Statement stmt = conexion.createStatement()) {
                stmt.execute(sql);
                System.out.println("Tabla 'producto' creada o ya existe.");
            } catch (SQLException e) {
                System.err.println("Error al crear la tabla producto: " + e.getMessage());
                throw e;
            }
        }

    @Override
    public void crear(Producto producto)throws SQLException {
        String sqlInsert = "INSERT INTO producto (nombre,descripcion,precio,cantidad,categoria_id) VALUES (?,?,?,?,?)";
        try(PreparedStatement ps = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getDescripcion());
            ps.setDouble(3,producto.getPrecio());
            ps.setInt(4,producto.getCantidad());
            ps.setLong(5,producto.getCategoria().getId());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo crear producto");
            }
            try(ResultSet rsId = ps.getGeneratedKeys()){
                if(rsId.next()){
                    producto.setId(rsId.getLong(1));
                }
            }
        }
    }
    @Override
    public Producto leer(Long id )throws SQLException{
        String sqlSelect = "SELECT p.*,c.nombre AS nombre_categoria,c.descripcion AS descripcion_categoria FROM producto p JOIN categoria c ON  p.categoria_id=c.id WHERE p.id=?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    return mapearProducto(rs);
                }else {
                    return  null;
                }
            }
        }
    }
    @Override
    public void actualizar(Producto producto) throws SQLException{
        String sqlUpdate = "UPDATE producto SET nombre=?,descripcion=?,precio=?,cantidad=?,categoria_id=? WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlUpdate)){
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getDescripcion());
            ps.setDouble(3,producto.getPrecio());
            ps.setInt(4,producto.getCantidad());
            ps.setLong(5,producto.getCategoria().getId());
            ps.setLong(6,producto.getId()); ;
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo actualizar producto");
            }
        }
    }
    @Override
    public void eliminar(Long id)throws SQLException{
        String sqlDelete = " DELETE FROM producto WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlDelete)){
            ps.setLong(1,id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo eliminar producto");
            }
        }
    }
    @Override
    public List<Producto> listar() throws SQLException{
        List<Producto> productos = new ArrayList<>();
        String sqlSelect = "SELECT p.*,c.nombre AS nombre_categoria,c.descripcion AS descripcion_categoria FROM producto p JOIN categoria c  ON  c.id = p.categoria_id";
        try(Statement ps=conexion.createStatement() ){
            try(ResultSet rs=ps.executeQuery(sqlSelect)){
                while (rs.next()){
                    productos.add(mapearProducto(rs));
                }
            }
            return productos;
        }
    }
    public boolean existeNombre(String nombre)throws SQLException{
        String sqlSelect = "SELECT COUNT(*) FROM producto WHERE nombre = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)) {
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
            return false;
        }
    }
    /*
    public List<Producto>listarPorCategoria(Long idCategoria)throws SQLException{
        List<Producto> productos = new ArrayList<>();
        String sqlSelect = "SELECT p.* FROM producto p JOIN categoria c ON c.id = p.categoria_id";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        }return productos;
    }
    public void MostrarProductoConCategoria(Long id)throws SQLException{
        String sqlSelect = "SELECT p.nombre AS nombre_producto ,c.nombre AS nombre_categoria FROM producto p JOIN categoria c ON c.id = p.categoria_id WHERE p.id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                System.out.println("Producto: "+ rs.getString("nombre_producto")
                + "\nCategoria: "+rs.getString("nombre_categoria"));
            }
        }
    }
    */
    public Producto mapearProducto(ResultSet rs)throws SQLException{
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getDouble("precio"));
        producto.setCantidad(rs.getInt("cantidad"));
        producto.setCategoria(mapearCategoria(rs));
        return producto;
    }
    public Categoria mapearCategoria(ResultSet rs)throws SQLException{
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        categoria.setDescripcion(rs.getString("descripcion_categoria"));
        return categoria;
    }

}
