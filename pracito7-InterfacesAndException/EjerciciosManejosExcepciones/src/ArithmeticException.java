import java.util.Scanner;

public class ArithmeticException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el valor: ");
        int num1 = sc.nextInt();
        System.out.print("\nIngrese el valor: ");
        int num2 = sc.nextInt();
        sc.nextLine();
        double division;

        try {
            division = num1 / num2;
        }catch (java.lang.ArithmeticException e){
            System.out.println("Error: No se puede dividir por cero.");
        }

    }
}
