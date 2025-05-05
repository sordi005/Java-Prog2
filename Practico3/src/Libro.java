import java.util.Scanner;

public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(int anioPublicacion) {

       if(anioPublicacion < 1900 || anioPublicacion > 2025){
           System.out.println(": No se puede modificar si el\n" +
                   "año es menor a 1900 o mayor al año actual.");
       }else{
         this.anioPublicacion = anioPublicacion;
         System.out.println("Año Guradado Correctamente.");
       }
    }


    public static void main(String[]args){
        System.out.println("Biblioteca");
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el titulo del libro:");
        String titulo = sc.nextLine();
        System.out.print("\nIngrese el autor del libro:");
        String autor = sc.nextLine();
        int anioPublicacion;
        while(true) {
           try {
               System.out.print("\nIngrese el año publicacion del libro:");
               anioPublicacion = sc.nextInt();
               if (anioPublicacion < 1900 || anioPublicacion > 2025) {
                   System.out.println("Año de Publicación Inválido");
               } else {
                   break;
               }
           }catch (Exception e){
               System.out.println("Ingrese un Valor Válido.");
           }
        }
        System.out.println();
        Libro miLibro = new Libro(titulo, autor, anioPublicacion);

        while(true){

            System.out.print("\n1_ Ver Información.\n2_ Modificar año publicado.\n3_Salir.\nOpcion:");
            int opcion = sc.nextInt();
            System.out.println();

            if(opcion == 3){break;}
            switch (opcion){

                case 1:
                    System.out.println("Titulo: " +miLibro.getTitulo());
                    System.out.println("Autor: " +miLibro.getAutor());
                    System.out.println("Publicación: " +miLibro.getAnioPublicacion());
                    break;
                case 2:

                    int nuevoAnio;
                    System.out.println("Modificar Año De Publicación:");
                    while(true){
                        System.out.print("Nuevo Año:");
                        try {
                            nuevoAnio = sc.nextInt();
                            break;
                        }catch (Exception e){System.out.println("Valor invalido");}
                    }
                    miLibro.setAnioPublicacion(nuevoAnio);

                default:
                    System.out.println("Opcion invalida");
            }



        }
        
        
    }
    
}
