import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Comparable<Pedido>, Identificable<Integer>{
    private int id;
    private List<Producto<?>>productos;
    private LocalDate fecha;

    public Pedido(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
        this.productos = new ArrayList<>();
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean tieneMismoId(Integer idIngresado) {
        return id == idIngresado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Producto<?>> getProductos() {
        return productos;
    }

    public void addProducto(Producto<?> producto) {
        this.productos.add(producto);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Double calcularTotal(){
        return productos.stream().mapToDouble(Producto:: getPrecio).sum();

    }
    @Override
    public int compareTo(Pedido pedido) {
        return Double.compare(this.calcularTotal(), pedido.calcularTotal());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", productos=" + productos +
                ", fecha=" + fecha +
                '}';
    }
}
