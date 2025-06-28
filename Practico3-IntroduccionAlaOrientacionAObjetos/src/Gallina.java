import java.util.Scanner;

public class Gallina {
    int id;
    int edad;
    int huevosPuestos;

    public Gallina(int id, int edad, int huevosPuestos) {
        this.id = id;
        this.edad = edad;
        this.huevosPuestos = huevosPuestos;
    }

    public void ponerHuevo() {
        System.out.println("Poniendo Huevo de Gallina "+id);
        huevosPuestos+=1;
        System.out.println("Huevo puestos: " + huevosPuestos);
    }
    public void envejecer(){
        System.out.println("Envejeciendo Gallina "+id);
        edad +=1;
        System.out.println("Edad: " + edad);
    }
    public void mostrarEstado(){
        System.out.println("------------------------------------------");
        System.out.println("\nGALLINA\nID: "+id+"\nEdad: "+edad+"\nHuevos puestos: "+huevosPuestos);
        System.out.println("------------------------------------------");

    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        Gallina gallina1 = new Gallina(1,2,6);
        Gallina gallina2 = new Gallina(2,1,3);

        gallina1.mostrarEstado();
        gallina2.mostrarEstado();
        System.out.println("Enter");
        sc.nextLine();
        gallina1.ponerHuevo();
        gallina2.ponerHuevo();
        System.out.println("Enter");

        sc.nextLine();
        gallina1.envejecer();
        gallina2.envejecer();
        System.out.println("Enter");

        sc.nextLine();
        gallina1.mostrarEstado();
        System.out.println("Enter");

        sc.nextLine();
        gallina2.mostrarEstado();



    }


}
