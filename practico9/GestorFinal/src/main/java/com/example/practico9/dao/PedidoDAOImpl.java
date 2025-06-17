package com.example.practico9.dao;

import com.example.practico9.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements GenericDao<Pedido>{
    private final Connection conexion;
    public PedidoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    public void crearTabla()throws SQLException{
        String sql = """
               CREATE TABLE IF NOT EXISTS pedidos (
               id INT AUTO_INCREMENT PRIMARY KEY,
               fecha DATE NOT NULL,
               total DECIMAL(10,2) NOT NULL
            );
            """;
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'pedidos' creada o ya existe.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla pedidos: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public void crear(Pedido pedido) throws SQLException {
        String sqlInsert = "INSERT INTO pedidos(fecha,total)  VALUES (?,?)";
        try(PreparedStatement ps = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)){
            ps.setDate(1, Date.valueOf((pedido.getFecha())));
            ps.setDouble(2,pedido.getTotal());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo crear pedido");
            }
            try(ResultSet rsId = ps.getGeneratedKeys()){
                if(rsId.next()){
                    pedido.setId(rsId.getLong(1));
                }
            }
        }
    }
    @Override
    public Pedido leer(Long id) throws SQLException {
        String sqlSelect = "SELECT * FROM pedidos WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapearPedido(rs);
                }
                return null;
            }
        }
    }
    @Override
    public void actualizar(Pedido pedido) throws SQLException {
        String sqlUpdate = "UPDATE pedidos SET fecha = ?, total = ? WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlUpdate)){
            ps.setDate(1, Date.valueOf(pedido.getFecha()));
            ps.setDouble(2, pedido.getTotal());
            ps.setLong(3, pedido.getId());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo actualizar pedido");
            }
        }
    }
    @Override
    public void eliminar(Long id) throws SQLException {
        String sqlDelete = "DELETE FROM pedidos WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlDelete)){
            ps.setLong(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo eliminar pedido");
            }
        }
    }
    public List<Pedido>listar()throws SQLException{
        List<Pedido>pedido = new ArrayList<>();
        String sqlSelect= "SELECT * FROM pedidos";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    while(rs.next()){
                        pedido.add(mapearPedido(rs));
                    }
                    return pedido;
                }
                return null;
            }
        }
    }
    public Pedido mapearPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("id"));
        pedido.setFecha(rs.getDate("fecha").toLocalDate());
        pedido.setTotal(rs.getDouble("total"));
        return pedido;
    }
    public boolean existeNombre(String nombre) throws SQLException {
        String sqlSelect = "SELECT * FROM producto WHERE nombre = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setString(1,nombre);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return true;
                }
            }
        }
        return false;
    }

}
