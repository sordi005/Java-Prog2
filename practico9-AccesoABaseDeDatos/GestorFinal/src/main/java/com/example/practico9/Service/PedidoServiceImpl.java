package com.example.practico9.Service;

import com.example.practico9.dao.ItemPedidoDAOImpl;
import com.example.practico9.dao.PedidoDAOImpl;
import com.example.practico9.dao.ProductoDAOImpl;
import com.example.practico9.model.ItemPedido;
import com.example.practico9.model.Pedido;
import com.example.practico9.model.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PedidoServiceImpl implements GenericService<Pedido> {
    private final PedidoDAOImpl pedidoDAO;
    private final ItemPedidoDAOImpl itemPedidoDAO;
    private final ProductoDAOImpl productoDAO;
    private final Connection conexion;
    public PedidoServiceImpl(Connection conexion,PedidoDAOImpl pedidoDAO,ItemPedidoDAOImpl itemPedidoDAO, ProductoDAOImpl productoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.itemPedidoDAO = itemPedidoDAO;
        this.productoDAO = productoDAO;
        this.conexion = conexion;
    }
    @Override
    public void crear(Pedido pedido)throws SQLException {
        validarPedido(pedido);
        porcesarPedido(pedido);
    }
    @Override
    public void actualizar(Pedido pedido)throws SQLException {
        if(pedido.getFecha() == null){
            throw new IllegalArgumentException("ERROR - Fecha no puede ser null");
        }
        if(pedido.getTotal() == null){
            throw new IllegalArgumentException("ERROR - Total no puede ser null");
        }
        pedidoDAO.actualizar(pedido);
    }
    @Override
    public Pedido leer(Long id)throws SQLException{
        if(id != null){
            return pedidoDAO.leer(id);
        }else throw new NullPointerException("ERROR - id no puede ser null");

    }
    @Override
    public List<Pedido> listar()throws SQLException{
        return pedidoDAO.listar();
    }
    @Override
    public void eliminar(Long id)throws SQLException{
        if(id != null){
            pedidoDAO.eliminar(id);
        }else throw new NullPointerException("ERROR - id no puede ser null");
    }
    private void validarPedido(Pedido pedido)throws IllegalArgumentException{
        if(pedido.getFecha() == null){
            throw new IllegalArgumentException("ERROR - Fecha no puede ser null");
        }
        if(pedido.getItems() == null || pedido.getItems().isEmpty()){
            throw new IllegalArgumentException("ERROR - El pedido debe contener al menos un ítem");
        }
        for(ItemPedido item : pedido.getItems()){
            if (item.getCantidad() <= 0){
                throw new IllegalArgumentException("ERROR - Cantidad Inválida.");
            }
        }
    }
    private void porcesarPedido(Pedido pedido)throws SQLException {
        try {
            conexion.setAutoCommit(false);
            validarItemsYcalcularTotales(pedido);
            pedidoDAO.crear(pedido);
            Producto producto;
            for (ItemPedido item : pedido.getItems()) {
                item.setPedido(pedido);
                itemPedidoDAO.crear(item);

                producto = item.getProducto();
                producto.setCantidad(producto.getCantidad() - item.getCantidad());
                productoDAO.actualizar(producto);
            }
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }
    private void validarItemsYcalcularTotales(Pedido pedido) throws SQLException{
        Double total = 0.0;
        for (ItemPedido item : pedido.getItems()){
            Producto producto = productoDAO.leer(item.getProducto().getId());
            if(producto == null){
                throw new IllegalArgumentException("ERROR - Producto no encontrado");
            }
            if(item.getCantidad() <= 0){
                throw new IllegalArgumentException("ERROR - Cantindad inválida.");
            }
            if(item.getCantidad() > producto.getCantidad()){
                throw new IllegalArgumentException("ERROR - Stock Insuficiente de "+ item.getProducto().getNombre());
            }
            Double subTotal = 0.0;
            subTotal = item.getCantidad() * producto.getPrecio();
            item.setSubTotal(subTotal);
            total += subTotal;
        }
        pedido.setTotal(total);
    }


}
