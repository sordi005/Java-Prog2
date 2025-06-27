import java.util.Scanner;

class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}
public class ValidacionEdad{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            try{
                System.out.print("Edad: ");
                int edad = sc.nextInt();

                if(edad<=0 || edad>100){
                    throw new EdadInvalidaException("Error: La edad debe estar entre 0 y 100 años.");
                }
                System.out.println("Edad: "+edad);
                break;
            }catch (EdadInvalidaException e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println("Ingrese un número válido.");
            }
        }

    }
    }

