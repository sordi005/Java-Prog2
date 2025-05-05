import javax.net.ssl.SSLContext;
import java.util.Scanner;


public class Estudiante {
    String lastName;
    String firstName;
    String curso;
    Double calificacion;

    public Estudiante(String lastName, String firstName, String curso, Double calificacion) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.curso = curso;
        this.calificacion = calificacion;
    }

    public void getInformacion() {
        System.out.printf("Estudiante %s %s\n",lastName,firstName);
        System.out.println("Curso:" + this.curso);
        System.out.println("Nota:"+this.calificacion);

    }
    public void subirCalificacion() {

        Scanner sc = new Scanner(System.in);

        double cantidad;

        while(true) {

            System.out.println("Aumento de nota.");
            System.out.print("Cuantos puntos desea aumentar : ");
            cantidad = sc.nextDouble();


            if (this.calificacion + cantidad > 10) {
                System.out.println("Atencion, La nota es mayor a 10 ");
            }else {
                this.calificacion += cantidad;
                System.out.println("Calificacion Guardada: " + calificacion );
                break;
            }

        }
    }
    public void bajarCalificacion() {

        Scanner sc = new Scanner(System.in);

        double cantidad;

        while(true) {
            System.out.println("Disminuir nota.");
            System.out.print("cuantos puntos desea disminuir: ");
            cantidad = sc.nextDouble();


            if (this.calificacion + cantidad <= 0) {
                System.out.printf("Atencion,  La nota es menor que 0");
            }else {
                this.calificacion += cantidad;
                System.out.print("Calificacion Guardada: " + calificacion );
            }

            this.calificacion += 1;

        }
    }

    public  static void main(String[] args) {

       Scanner sc = new Scanner(System.in);
       System.out.println("Gestor de Notas");
        String lastname;
        String firstname;
        String curso;
        Double calificacion;

        while (true){

            try {
                System.out.print("Nombre:");
                lastname = sc.nextLine();

                System.out.print("\nApellido:");
                firstname = sc.nextLine();

                System.out.print("\nCurso:");
                curso = sc.nextLine();

                System.out.print("\nCalificación:");
                calificacion = sc.nextDouble();

                if (calificacion < 0 || calificacion>10){
                    System.out.println("\nNota Invalida");
                }
                else {System.out.println(); break;}
            }
            catch (Exception e){
                System.out.println("Error");
            }


        }

        Estudiante estudiante =new Estudiante(lastname,firstname,curso,calificacion);
        while (true){
            System.out.print("1_ Ver Información:\n2_ Subir Nota\n3_ Bajar Nota\n4_ Salir");
            int opcion = sc.nextInt();
            System.out.println();
            switch (opcion){
                case 1 :
                    estudiante.getInformacion();
                    break;

                case 2: estudiante.subirCalificacion();
                     break;
                case 3: estudiante.bajarCalificacion();
                    break;
                case 4:break;

            }
        }


    }


}
