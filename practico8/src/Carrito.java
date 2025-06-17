import java.util.ArrayList;
import java.util.List;

public class Carrito <T extends Producto<?>>{
    private List<T> productos = new ArrayList<>();

    public void agregarProducto(T producto){
        this.productos.add(producto);
    }
    public void eliminarProducto(T producto) {
        productos.remove(producto);
    }
    public Double obtenerTotal(){
        return productos.stream().mapToDouble(Producto:: getPrecio).sum();
    }
    public void mostrarProductos(){
        System.out.println("Lista de productos:");
        for(T producto : productos){
            System.out.println("-------------------------");
            System.out.println(producto);
        }
        System.out.println("-------------------------");

    }
}
