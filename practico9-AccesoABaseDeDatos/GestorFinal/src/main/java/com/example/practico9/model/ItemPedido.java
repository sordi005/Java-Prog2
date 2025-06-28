package com.example.practico9.model;

public class ItemPedido {
    private Long id;
    private int cantidad;
    private Double subTotal;
    private Producto producto;
    private Pedido pedido;

    public ItemPedido(){}
    public ItemPedido(int cantidad,Producto producto){
        this.cantidad = cantidad;
        this.producto = producto;
    }
    public ItemPedido(int cantidad, Double subTotal){
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", subTotal=" + subTotal +
                ", producto=" + producto +
                ", pedido=" + pedido +
                '}';
    }
}
