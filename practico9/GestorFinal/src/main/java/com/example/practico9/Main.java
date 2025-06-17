package com.example.practico9;

import com.example.practico9.Service.PedidoServiceImpl;
import com.example.practico9.Service.ProductoServiceImpl;
import com.example.practico9.dao.CategoriaDAOImpl;
import com.example.practico9.dao.ItemPedidoDAOImpl;
import com.example.practico9.dao.PedidoDAOImpl;
import com.example.practico9.dao.ProductoDAOImpl;
import com.example.practico9.model.Categoria;
import com.example.practico9.model.ItemPedido;
import com.example.practico9.model.Pedido;
import com.example.practico9.model.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Antes de correr el programa se debe
 - crear la bases de datos en mysql
 - ingresar credenciales de mysql en el pool de conexiones
 */

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        creacionTablas();
        guardarProductosDb();
        inicio();
    }
    public static void inicio() throws SQLException {
        int opcion = 0;
        while(opcion!=2){
            System.out.print(" -- Pedidos -- " +
                    "\n 1 -- Hacer Pedido." +
                    "\n 2 -- Salir");
            while (true) {
                try {
                    System.out.print("\nOpcion: ");
                    opcion = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Opcion Inválida\n");
                }
                if (opcion != 1 & opcion != 2) {
                    System.out.println("Opción Inválida.\n");
                }
                if (opcion == 1) {
                    hacerPedido();
                    break;
                }
                break;
            }
        }
    }
    public static void hacerPedido () throws SQLException {
        Pedido pedidoProgreso = new Pedido(LocalDate.of(2025, 06 ,23));
        List<Producto> productosDisponibles = getProdcutosDisponibles();
        int opcion = 0;
         while (opcion!=5){
            System.out.println("\n ---- pedidos --- " +
                    "\n1 - Listar Productos Disponibles." +
                    "\n2 - Agregar Producto." +
                    "\n3 - Ver Carrito" +
                    "\n4 - Realizar Compra" +
                    "\n5 - Volver.");
            try {
                System.out.print("Opcion: ");
                opcion = sc.nextInt();
                System.out.println();
                switch (opcion) {
                    case 1:
                        mostrarProductosDisponibles(productosDisponibles);
                        break;
                    case 2:
                        ItemPedido itemPedido = crearItemPedido();
                        if (itemPedido!= null) {
                            pedidoProgreso.addItem(itemPedido);
                            System.out.println("\n -- Producto agregado: "+itemPedido.getProducto().getNombre()+"("+itemPedido.getCantidad()+")\n");
                        }
                        break;
                    case 3:
                        getCarrito(pedidoProgreso);
                        break;
                    case 4:
                        if(pedidoProgreso.getItems().isEmpty()) {
                            System.out.println("\n -- No se han seleccionado productos.\n");
                        }else realizarCompra(pedidoProgreso);
                        opcion = 5;
                        break;
                    case 5:
                        break;
                    default:
                        throw new IllegalStateException("Opcion Inválida:\n");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\nOpcion Inválida\n");
            }
        }
    }

    ///  Conseguir productos
    private static void mostrarProductosDisponibles(List<Producto> productosDisponibles) {
        System.out.println("\n--- Listado de Productos Disponibles ---");
        for (Producto producto : productosDisponibles) {
            System.out.println("----------- producto -------------");
            System.out.println("-- ID: "+producto.getId());
            System.out.println("- Nombre: "+producto.getNombre());
            System.out.println("- Precio: $"+producto.getPrecio());
            System.out.println();
        }
        System.out.println("--------------------------------------------\n");
    }
    private static List<Producto> getProdcutosDisponibles()throws SQLException{
        List<Producto> productosDisponibles = new ArrayList<>();
        try(Connection conexion = DbConection.getConnection()){
            CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(conexion);

            ProductoDAOImpl productoDAO = new ProductoDAOImpl(conexion);
            ProductoServiceImpl productoService = new ProductoServiceImpl(productoDAO,categoriaDAO);

            productosDisponibles = productoService.listar();
            return productosDisponibles;
        }
    }
    private static Producto getProductoById(Long id)throws SQLException {
        try (Connection conexion = new DbConection().getConnection()) {
            CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(conexion);
            ProductoDAOImpl productoDAO = new ProductoDAOImpl(conexion);
            ProductoServiceImpl productoService = new ProductoServiceImpl(productoDAO, categoriaDAO);
            Producto producto = productoService.leer(id);
            return producto;
        } catch (SQLException e) {
            System.out.println("\nError al leer el producto");
        }
        return null;
    }

    private static ItemPedido crearItemPedido() {
        Producto producto;
        Long id;
        try {
            while(true) {
                System.out.print("\nIngrese el id del producto( Ingrese 0 para volver )");
                id = sc.nextLong();
                if(id == 0){
                    return null;
                }
                producto = getProductoById(id);
                if (producto == null) {
                    System.out.println("No se encontro el producto");
                }else break;
            }
            int cantidadIngresada;
            while (true){
                try{
                    System.out.print("Cantidad (Stock disponible "+ producto.getCantidad()+"): ");
                    cantidadIngresada = sc.nextInt();
                    System.out.println();
                    if(cantidadIngresada == 0 || cantidadIngresada > producto.getCantidad()){
                        System.out.println("Stock Insuficiente");
                    }else {
                        ItemPedido itemActual = new ItemPedido(cantidadIngresada,producto);
                        return itemActual;
                    }
                }catch(Exception e){
                    System.out.println("Cantidad Inválida");
                }
            }
        }catch (IllegalArgumentException | SQLException e) {
            System.out.println("\nId incorrecto");
        }
        return null;
    }

    private static void getCarrito(Pedido pedido) {
        List<ItemPedido> items = pedido.getItems();
        System.out.println("\n--- Listado de Carrito ---");
        if (items.size()==0) {
            System.out.println("\nNo hay productos seleccionados\n");
        }else {
            for (ItemPedido item : items) {
                System.out.println("--------------------------");
                System.out.println("Producto: "+item.getProducto().getNombre());
                System.out.println("Cantidad: "+item.getCantidad());
                System.out.println("--------------------------");
            }
            System.out.println("--------------------------\n");
        }
    }
    private static void realizarCompra(Pedido pedido)throws SQLException{
        try(Connection conexion= DbConection.getConnection()){
            PedidoDAOImpl pedidoDAO = new PedidoDAOImpl(conexion);
            ItemPedidoDAOImpl itemPedidoDAO = new ItemPedidoDAOImpl(conexion);
            ProductoDAOImpl productoDAO = new ProductoDAOImpl(conexion);

            PedidoServiceImpl pedidoService = new PedidoServiceImpl(conexion,pedidoDAO,itemPedidoDAO,productoDAO);
            pedidoService.crear(pedido);
        }
        finalPedido(pedido);
    }
    private static void finalPedido(Pedido pedido) throws SQLException {
       int opcion = 0;
        while (opcion !=2) {
           System.out.println("\n --- Pedido Completado ---" +
                   "\n1 - Ver Factura" +
                   "\n2 - Menu");
           while (true) {
               try {
                   System.out.print("\nOpción: ");
                   opcion = sc.nextInt();
                   System.out.println();
                   if (opcion == 1) {
                       pedido.getFactura();
                       break;
                   }
                   if (opcion == 2) {
                       break;
                   } else System.out.println("Opcion Inválida\n");

               } catch (Exception e) {
                   System.out.println("\nOpcion no valida");
               }
           }
       }
    }
    private static void guardarProductosDb() throws SQLException {
        ///  Creacion de Cateorias  ///
        Categoria categoriaBebida = new Categoria("Bebidas", "Toda Clase de Bebidas");
        Categoria categoriaLimpieza = new Categoria("Limpieza", "Artiuclos de limpieza");
        Categoria categoriaComida = new Categoria("Comida", "Toda La Comida");
        ///  creacion de Productos ///
        Producto productoCocacola = new Producto("CocaCola", "Envase descartable 1L", 2500.0, 10);
        Producto productQuilmes = new Producto("Quilmes", "Cerveza rubia Envase virio 1L", 3200.0, 10);

        Producto productoDetergente = new Producto("Detergente", "750ml", 2500.0, 10);
        Producto productoCif = new Producto("cif", "cif 1l", 2000.0, 20);

        Producto productoPapafrita = new Producto("Papafrita", "Papafrita", 2500.0, 10);
        Producto productoGalletas = new Producto("Oreo", "Papafrita", 1200.0, 10);

        productoCocacola.setCategoria(categoriaBebida);
        productQuilmes.setCategoria(categoriaBebida);
        productoDetergente.setCategoria(categoriaLimpieza);
        productoCif.setCategoria(categoriaLimpieza);
        productoGalletas.setCategoria(categoriaComida);
        productoPapafrita.setCategoria(categoriaComida);

        try(Connection connection = DbConection.getConnection()){
            CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(connection);
            ProductoDAOImpl productoDAO = new ProductoDAOImpl(connection);

            categoriaDAO.crear(categoriaBebida);
            categoriaDAO.crear(categoriaLimpieza);
            categoriaDAO.crear(categoriaComida);

            productoDAO.crear(productoCocacola);
            productoDAO.crear(productQuilmes);
            productoDAO.crear(productoDetergente);
            productoDAO.crear(productoCif);
            productoDAO.crear(productoPapafrita);
            productoDAO.crear(productoGalletas);
        }
    }
    private static  void creacionTablas()throws SQLException {
        try (Connection conexion = DbConection.getConnection()) {
            CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(conexion);
            ProductoDAOImpl productoDAO = new ProductoDAOImpl(conexion);
            PedidoDAOImpl pedidoDAO = new PedidoDAOImpl(conexion);
            ItemPedidoDAOImpl itemPedidoDAO = new ItemPedidoDAOImpl(conexion);

            categoriaDAO.crearTabla();
            productoDAO.crearTabla();
            pedidoDAO.crearTabla();
            itemPedidoDAO.crearTabla();
        }
    }
}



