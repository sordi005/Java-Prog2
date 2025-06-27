import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TryWithResource {
    public static void main(String[] args){
        String pathfile = "C:\\workspace\\University\\java\\git\\practicos\\Java-Prog2\\pracito7-InterfacesAndException\\EjerciciosManejosExcepciones\\src\\file";
         try (BufferedReader br = new BufferedReader(new FileReader(pathfile))) {

        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        System.out.println("Conexion Cerrada");
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }}
}



