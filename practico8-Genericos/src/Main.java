// Main.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // === Kata 1: Crear productos con diferentes identificadores ===
        System.out.println("=== Kata 1: Manejo de Productos ===");
        List<Producto<?>> productos = new ArrayList<>();
        Producto<String> laptop = new Producto<>("SKU123", "Laptop", 999.99);
        Producto<Integer> mouse = new Producto<>(1001, "Mouse", 29.99);
        Producto<String> teclado = new Producto<>("SKU456", "Teclado", 59.99);
        productos.add(laptop);
        productos.add(mouse);
        productos.add(teclado);

        System.out.println("Lista de productos:");
        productos.forEach(System.out::println);

        // === Kata 2: Usar el carrito ===
        System.out.println("\n=== Kata 2: Carrito de Compras ===");
        Carrito<Producto<?>> carrito = new Carrito<>();
        carrito.agregarProducto(laptop);
        carrito.agregarProducto(mouse);
        carrito.mostrarProductos();
        System.out.println("Total del carrito: $" + carrito.obtenerTotal());

        // Eliminar un producto y recalcular
        carrito.eliminarProducto(mouse);
        System.out.println("\nDespués de eliminar el mouse:");
        carrito.mostrarProductos();
        System.out.println("Total del carrito: $" + carrito.obtenerTotal());

        // === Kata 3: Crear y ordenar pedidos ===
        System.out.println("\n=== Kata 3: Comparación y Ordenación de Pedidos ===");
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido1 = new Pedido(1, LocalDate.of(2025, 6, 1));
        pedido1.addProducto(laptop);
        pedido1.addProducto(teclado);

        Pedido pedido2 = new Pedido(2, LocalDate.of(2025, 5, 1));
        pedido2.addProducto(mouse);

        pedidos.add(pedido1);
        pedidos.add(pedido2);

        // Ordenar por total (Comparable)
        System.out.println("Pedidos ordenados por total:");
        pedidos.forEach(System.out::println);

        // Ordenar por fecha (Comparator)
        System.out.println("\nPedidos ordenados por fecha:");
        Collections.sort(pedidos, new comparadorPedidosPorFecha());
        pedidos.forEach(System.out::println);

        // === Kata 4: Buscar pedidos ===
        System.out.println("\n=== Kata 4: Búsqueda Genérica de Pedidos ===");
        Buscador<Pedido, Integer> buscador = new Buscador<>();
        Pedido encontrado = buscador.buscar(pedidos, 1);
        System.out.println("Pedido encontrado con ID 1: " + encontrado);

        // Buscar un pedido que no existe
        Pedido noEncontrado = buscador.buscar(pedidos, 999);
        System.out.println("Pedido con ID 999: " + (noEncontrado == null ? "No encontrado" : noEncontrado));
    }
}