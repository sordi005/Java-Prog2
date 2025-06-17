package com.example.practico9.model;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDate fecha;
    private List<ItemPedido>items;
    private Double total;

    public Pedido() {}
    public Pedido(LocalDate fecha){
        this.id = id;
        this.fecha = fecha;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
    public void addItem(ItemPedido item){
        if (item!= null){
            items.add(item);
        }
    }
    public void getFactura()throws SQLException,IllegalArgumentException{
            System.out.println(" ---- DETALLES DE PEDIDO ----");
            System.out.println(" -- Fecha: "+ getFecha());
            for (ItemPedido item: getItems()){
                System.out.println(
                        "--------------------Item-----------------"
                                +"\nProducto: "+ item.getProducto().getNombre()
                                +"\nCategoría: "+item.getProducto().getCategoria().getNombre()
                                +"\nCantidad: "+ item.getCantidad()
                                +"\nSubTotal: $"+ item.getSubTotal()
                                +"\n----------------------------------------");
            }
            System.out.println("TOTAL: $"+getTotal());
            System.out.println("--------------------------------------");
        }
    }

