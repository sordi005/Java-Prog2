import java.io.File;
import java.util.Scanner;

public class FileNotFoundException {
    public static void main(String[] args) {
        try{
            File file = new File("C:\\workspace\\University\\java\\git\\practicos\\Java-Prog2\\pracito7-InterfacesAndException\\EjerciciosManejosExcepciones\\src\\file");
            Scanner lector = new Scanner(file);
            while(lector.hasNextLine()){
                String line = lector.nextLine();
                System.out.println(line);
            }
            lector.close();
        }catch (java.io.FileNotFoundException e){
            System.out.println("Error:  El archivo no existe o no se puede leer");
        }
    }
}
