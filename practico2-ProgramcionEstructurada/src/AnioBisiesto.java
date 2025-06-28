import java.util.Scanner;

public class AnioBisiesto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un año: ");
        int ano = scanner.nextInt();

        boolean esBisiesto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

        System.out.println("El año " + ano + (esBisiesto ? " es bisiesto." : " no es bisiesto."));
        scanner.close();
    }
}