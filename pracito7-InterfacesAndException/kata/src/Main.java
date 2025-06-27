public class Main {
    public static void main(String[] args) {
        System.out.println("----- Creando Productos ----");
        Producto p1 = new Producto("CocaCola",2500);
        Producto p2 = new Producto("Empanadas",6000);
        Producto p3 = new Producto("pizza",9000);
        Producto p4 = new Producto("sprite",2500);

        System.out.println("\n----- Creando Clientes ----");
        Cliente c1 = new Cliente("pedro","pedro@gmail.com");
        Cliente c2 = new Cliente("Juan","Juan@ghhotmail.com");
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("\n----- Creando Pedidos ----");
        Pedido pedido1=new Pedido(EstadoPedido.PENDIENTE, c1);
        pedido1.addProducto(p3);
        pedido1.addProducto(p4);
        Pedido pedido2= new Pedido(EstadoPedido.PENDIENTE, c2);
        pedido2.addProducto(p1);
        pedido2.addProducto(p2);
        pedido2.addProducto(p3);

        System.out.println("\n----- Pedidos ----");
        pedido1.MostrarPedido();
        pedido2.MostrarPedido();

        System.out.println("\n----- Creando Trajeta de credito ----");
        TarjetaCredito t1 = new TarjetaCredito("1234-1234-1234-1234");

        System.out.println("\n----- Creando Cliente.Paypal ----");
        Cliente.Paypal py =  new Cliente.Paypal("paypal@paypal.com");


        System.out.println(t1);
        System.out.println(py);

        System.out.println("\n----- Realizar pago con Trajeta de credito ----");
        t1.aplciarDescuento(10);
        t1.procesarPago(pedido1.calcularTotal());
        pedido1.cambiarEstado(EstadoPedido.ENVIADO);


        System.out.println("\n----- Realizar pago con Cliente.Paypal ----");
        py.procesarPago(p2.calcularTotal());
        pedido1.cambiarEstado(EstadoPedido.ENVIADO);

        System.out.println("\n----- Fin del pedido ----");
        pedido1.cambiarEstado(EstadoPedido.ENTREGADO);
        pedido2.cambiarEstado(EstadoPedido.ENTREGADO);


    }
}
