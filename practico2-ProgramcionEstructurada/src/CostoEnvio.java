import java.util.Scanner;

public class CostoEnvio {
    public static double calcularCostoEnvio(double peso, String zona) {
        return peso * (zona.equalsIgnoreCase("Nacional") ? 5 : 10);
    }

    public static double calcularTotalCompra(double precioProducto, double costoEnvio) {
        return precioProducto + costoEnvio;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();

        System.out.print("Ingrese el peso del paquete en kg: ");
        double peso = scanner.nextDouble();

        System.out.print("Ingrese la zona de envío (Nacional/Internacional): ");
        String zona = scanner.next();

        double envio = calcularCostoEnvio(peso, zona);
        double total = calcularTotalCompra(precio, envio);

        System.out.println("El costo de envío es: " + envio);
        System.out.println("El total a pagar es: " + total);
        scanner.close();
    }
}