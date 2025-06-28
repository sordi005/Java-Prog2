import java.util.Scanner;

public class SumaPares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int suma = 0;
        int numero;

        do {
            System.out.print("Ingrese un número (0 para terminar): ");
            numero = scanner.nextInt();
            if (numero % 2 == 0 && numero != 0) {
                suma += numero;
            }
        } while (numero != 0);

        System.out.println("La suma de los números pares es: " + suma);
        scanner.close();
    }
}
