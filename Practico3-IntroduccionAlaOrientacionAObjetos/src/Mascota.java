import java.util.Scanner;

public class Mascota {
    String nombre;
    String especie;
    int edad;

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    public void mostrarInfo(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Especie: " + especie);
        System.out.println("Edad: " + edad);
    }

    public void cumplirAnio(){
        this.edad = edad + 1;
        System.out.println("Edad Actualizada ");
        mostrarInfo();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrse la Mascota");
        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        System.out.println("Especie:");
        String especie = sc.nextLine();
        System.out.println("Edad:");
        int edad = sc.nextInt();

        Mascota mascota = new Mascota(nombre, especie, edad);

        while(true) {
            System.out.println("1_Ver Información de la Mascota.\n2_ Cumplir año.\n3_ Salir.");
            System.out.print("Opcion:");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    mascota.mostrarInfo();
                    break;
                case 2:
                    mascota.cumplirAnio();
                    break;

            }
            if (opcion == 3){break;}

        }

    }


}
