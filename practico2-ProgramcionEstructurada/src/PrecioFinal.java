import java.util.Scanner;

public class PrecioFinal {
    public static double calcularPrecioFinal(double precioBase, double impuesto, double descuento) {
        return precioBase + (precioBase * impuesto/100) - (precioBase * descuento/100);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el precio base del producto: ");
        double precio = scanner.nextDouble();

        System.out.print("Ingrese el impuesto en porcentaje (Ejemplo: 10 para 10%): ");
        double impuesto = scanner.nextDouble();

        System.out.print("Ingrese el descuento en porcentaje (Ejemplo: 5 para 5%): ");
        double descuento = scanner.nextDouble();

        double precioFinal = calcularPrecioFinal(precio, impuesto, descuento);
        System.out.println("El precio final del producto es: " + precioFinal);
        scanner.close();
    }
}
