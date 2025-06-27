import java.util.ArrayList;
import java.util.List;

public class Pedido implements Pagable {
    EstadoPedido estado;
    Cliente cliente;
    List<Producto> productos;

    public Pedido(){
        productos = new ArrayList<Producto>();
    }

    public Pedido(EstadoPedido estado, Cliente cliente) {
        this.estado = estado;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        List<Producto> productos;
    }

    @Override
    public Double calcularTotal() {
        double total = productos.stream()
                .mapToDouble(Producto::calcularTotal)
                .sum();
        return total;
    }
    public EstadoPedido getEstado() {
        return estado;
    }
    public void cambiarEstado(EstadoPedido estado) {
        this.estado = estado;
        cliente.notificar("Etsado de pedido actualizado: "+estado);
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public void addProducto(Producto producto){
        productos.add(producto);
    }
    public void MostrarPedido(){
        System.out.println("------ DETALELS DE PEDIDO -------");
        System.out.println("Cliente: "+ cliente.getNombre());
        System.out.println("Estado"+ estado);
        for( Producto producto: productos){System.out.println(producto);};
        System.out.println("------ ------ -------");

    }


}
