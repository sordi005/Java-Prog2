import java.util.Scanner;

public class DescuentoEspecial {
    static final double DESCUENTO_ESPECIAL = 0.10;

    public static void calcularDescuentoEspecial(double precio) {
        double descuentoAplicado = precio * DESCUENTO_ESPECIAL;
        double precioFinal = precio - descuentoAplicado;

        System.out.println("El descuento especial aplicado es: " + descuentoAplicado);
        System.out.println("El precio final con descuento es: " + precioFinal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        calcularDescuentoEspecial(precio);
        scanner.close();
    }
}