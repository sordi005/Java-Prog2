import java.util.Scanner;

public class GestionStock {
    public static int actualizarStock(int stockActual, int cantidadVendida, int cantidadRecibida) {
        return stockActual - cantidadVendida + cantidadRecibida;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el stock actual del producto: ");
        int stock = scanner.nextInt();

        System.out.print("Ingrese la cantidad vendida: ");
        int vendidos = scanner.nextInt();

        System.out.print("Ingrese la cantidad recibida: ");
        int recibidos = scanner.nextInt();

        int nuevoStock = actualizarStock(stock, vendidos, recibidos);
        System.out.println("El nuevo stock del producto es: " + nuevoStock);
        scanner.close();
    }
}