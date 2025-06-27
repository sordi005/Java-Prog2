import java.util.Scanner;

public class NumberFormatException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um numero entero: ");
        String num = sc.nextLine();
        try {
            int numeroModificado = Integer.parseInt(num);
        }catch (java.lang.NumberFormatException e){
            System.out.println("Error: Ingreso un valor no numérico");
        }

    }
}
