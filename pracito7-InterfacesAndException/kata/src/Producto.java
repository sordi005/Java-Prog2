public class Producto implements Pagable {
    private String nombre;
    private double precio;
    public Producto(){
    }
    public Producto( String nombre,double precio) {
        this.precio = precio;
        this.nombre = nombre;
    }

    @Override
    public Double calcularTotal() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
