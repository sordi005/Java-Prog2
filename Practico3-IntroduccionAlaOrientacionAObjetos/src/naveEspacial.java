import java.util.Scanner;

public class naveEspacial {

    String nombre;
    int combustible;

    public static void main(String[] args) {

        String nombre;
        int combustible;

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar Nave Espacial");
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        while(true) {
            System.out.print("Combustible: ");
            combustible = sc.nextInt();
            if (combustible < 0 || combustible > 100) {
                System.out.println("Combustible no valido");
            }else break;
        }
        naveEspacial miNave = new naveEspacial(nombre,combustible) ;


        while(true) {

            System.out.print("Simulador");
            System.out.println("\n--------------------");
            System.out.println("1 : Despegar.");
            System.out.println("2 : Avanzar.");
            System.out.println("3 : Cargar Combustible");
            System.out.println("4 : Ver estado.");
            System.out.println("5 : Terminar Simulación.");
            System.out.println("----------------------");
            int opcion;
            do {
                System.out.print("Opcioón: ");
                opcion = sc.nextInt();
            }while(opcion < 1 || opcion > 5);

            switch (opcion){
                case 1:
                    miNave.despegar();
                    break;
                case 2:
                    miNave.avanzar();
                    break;
                case 3:
                    int cantidad;
                    System.out.println("Recarga de Combustible");
                    System.out.println("Combustible Actual: "+miNave.combustible);
                    System.out.print("Cantidad: ");
                    cantidad = sc.nextInt();
                    miNave.recargarCombustible(cantidad);
                    break;
                case 4:
                    miNave.mostrarEstado();
                    break;
            }
            if (opcion == 5){
                break;
            }





        }

    }

    public naveEspacial(String nombre, int combustible) {
        this.nombre = nombre;
        this.combustible = combustible;
    }

    public void mostrarEstado() {
        System.out.println("Estado De La Nave");
        System.out.println("-----------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Combustible: " + combustible);
        System.out.println("-----------------------");
    }

    public void recargarCombustible(int cantCombustible) {
        int cantTotal = combustible+cantCombustible;
        if(cantTotal > 100) {
            System.out.println("Recarga Invalida. La canitdad pasa el limite,");
        }
        else {
            combustible = cantTotal;
        }
    }
    public void avanzar() {
        if (combustible > 0) {
            combustible -= 1;
            System.out.println("Avanzando...");
        }else {
            System.out.println("No hay combustible");
        }
    }
    public void despegar(){
        if(combustible > 10){
            combustible -= 10;
            System.out.println("Despegando...");
        }else {
            System.out.println("No hay combustible");
        }
    }




}
