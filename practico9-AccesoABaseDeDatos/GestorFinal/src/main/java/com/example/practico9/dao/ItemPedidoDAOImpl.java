package com.example.practico9.dao;

import com.example.practico9.model.Categoria;
import com.example.practico9.model.ItemPedido;
import com.example.practico9.model.Pedido;
import com.example.practico9.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAOImpl implements GenericDao<ItemPedido>{
    private final Connection conexion;
    public ItemPedidoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }
    public void crearTabla()throws SQLException{
        String sql = """
                  CREATE TABLE IF NOT EXISTS items_pedido (
                  id INT AUTO_INCREMENT PRIMARY KEY,
                  pedido_id INT NOT NULL,
                  producto_id INT NOT NULL,
                  cantidad INT NOT NULL,
                  subTotal DECIMAL(10,2) NOT NULL,
                  FOREIGN KEY (pedido_id) REFERENCES pedidos(id), -- Comportamiento predeterminado: RESTRICT
                  FOREIGN KEY (producto_id) REFERENCES producto(id) -- Comportamiento predeterminado: RESTRICT
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
    public void crear(ItemPedido item) throws SQLException {
        String sqlInsert = "INSERT INTO items_pedido(pedido_id,producto_id,cantidad,subTotal)  VALUES (?,?,?,?)";
        try(PreparedStatement ps = conexion.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, item.getPedido().getId());
            ps.setLong(2, item.getProducto().getId());
            ps.setDouble(3, item.getCantidad());
            ps.setDouble(4, item.getSubTotal());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo crear el ítem de pedido");
            }
            try(ResultSet rsId = ps.getGeneratedKeys()){
                if(rsId.next()){
                    item.setId(rsId.getLong(1));
                }
            }
        }
    }
    @Override
    public ItemPedido leer(Long id) throws SQLException {
        String sqlSelect = "SELECT i.id AS id_items,i.cantidad AS cantidad_items,i.subTotal AS subtotal_items,"
                +"p.id AS id_pedido,p.fecha AS fecha_pedido,p.total AS total_pedido,"
                +"pr.id AS producto_id, pr.nombre AS nombre_producto, pr.descripcion AS descripcion_producto,pr.precio AS precio_producto,"
                +"pr.cantidad AS cantidad_producto,c.id AS categoria_id, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                +"FROM items_pedido i "
                +"LEFT JOIN pedido p ON pe.id = i.id_pedido "
                +"LEFT JOIN producto pr ON pr.id = i.producto_id "
                +"LEFT JOIN categoria c ON c.id = po.categoria_id WHERE i.id = ?";;
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapearItemPedido(rs);
                }
                return null;
            }
        }
    }
    @Override
    public void actualizar(ItemPedido item) throws SQLException {
        String sqlUpdate = "UPDATE ItemPedido SET pedido_id = ?, producto_id = ?,cantidad = ?,subTotal = ? WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlUpdate)){
            ps.setLong(1,item.getPedido().getId());
            ps.setLong(2,item.getProducto().getId());
            ps.setDouble(3,item.getCantidad());
            ps.setDouble(4,item.getSubTotal());
            ps.setLong(5,item.getId());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo Actualizar el ítem de pedido");
            }
        }
    }
    public List<ItemPedido> listar()throws SQLException{
        List<ItemPedido>itemsPedidos = new ArrayList<>();
        String sqlSelect=
                "SELECT i.id AS id_items,i.cantidad AS cantidad_items,i.subTotal AS subtotal_items,"
                +"p.id AS id_pedido,p.fecha AS fecha_pedido,p.total AS total_pedido,"
                +"pr.id AS producto_id, pr.nombre AS nombre_producto, pr.descripcion AS descripcion_producto,pr.precio AS precio_producto,"
                +"pr.cantidad AS cantidad_producto,c.id AS categoria_id, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                +"FROM items_pedido i "
                +"LEFT JOIN pedido p ON pe.id = i.id_pedido "
                +"LEFT JOIN producto pr ON pr.id = i.producto_id "
                +"LEFT JOIN categoria c ON c.id = po.categoria_id ";
        try(PreparedStatement ps = conexion.prepareStatement(sqlSelect)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    itemsPedidos.add(mapearItemPedido(rs));
                }
                return itemsPedidos;
            }
        }
    }
    @Override
    public void eliminar(Long id) throws SQLException{
        String sqlDelete = "DELETE FROM items_pedido WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sqlDelete)){
            ps.setLong(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo eliminar el ítem de pedido");
            }
        }
    }

    public ItemPedido mapearItemPedido(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();
        item.setId(rs.getLong("id_items"));
        item.setPedido(mapearPedido(rs));
        item.setProducto(mapearProducto(rs));
        item.setCantidad(rs.getInt("cantidad_items"));
        item.setSubTotal(rs.getDouble("subTotal_items"));
        return item;
    }
    public Pedido mapearPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("id_pedido"));
        pedido.setFecha(rs.getDate("fecha_pedido").toLocalDate());
        pedido.setTotal(rs.getDouble("total_pedido"));
        return pedido;
    }
    public Producto mapearProducto(ResultSet rs)throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id_producto"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setDescripcion(rs.getString("descripcion_producto"));
        producto.setPrecio(rs.getDouble("precio_producto"));
        producto.setCantidad(rs.getInt("cantidad_producto"));
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
