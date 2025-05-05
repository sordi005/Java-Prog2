import java.awt.event.TextEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SistemaSoporte {

    private final Scanner sc = new Scanner(System.in);

    private ArrayList<Usuario>listaUsuarios;
    private ArrayList<Tecnico>tecnicos;
    private ArrayList<TicketSoporte>tickets;

    public SistemaSoporte() {
        listaUsuarios = new ArrayList<>();
        tecnicos = new ArrayList<>();
        tickets = new ArrayList<>();
    }
    public static void main(String[] args) {
        SistemaSoporte program = new SistemaSoporte();
        program.ejecutarMenu();

    }

    public void ejecutarMenu(){
       int opcion;
        do {
           inicioPrincipal();
            opcion = ingresarOpcion();
           switch (opcion) {
               case 1:
                   TicketSoporte ticket = crearTicket();
                   tickets.add(ticket);
                   System.out.println("Ticket creado.");
                   ticket.mostrarDestalle();
                   System.out.println("Enter Para continuar.");
                   sc.nextLine();
                   break;
               case 2:
                   mostrarTickets();
                   System.out.println("Enter Para continuar.");
                   sc.nextLine();
                   break;
               case 3:
                   asignarTecnico();
                   break;
           }
       }while (opcion != 4);
        }
    public void inicioPrincipal() {
        System.out.println(
        "======= Sistema de Soporte ======="
        + "\n1. Crear Ticket."
        + "\n2. Consultar Tickets."
        + "\n3. Asignar Técnico."
        + "\n4. Salir."
        + "\n===============================");
    }
    //metodo para ingresar una opcion valida.
    private int ingresarOpcion(){
        int opcionIngresada = 0;
        do {
            try {
                System.out.print("Ingrese una opcion: ");
                opcionIngresada = sc.nextInt();
                if (opcionIngresada < 1 || opcionIngresada > 4) {
                    System.out.println("Ingrese un numero válido");
                }
            } catch (Exception e) {
                System.err.println("Ingrese un numero valido.");
            }
        }while (opcionIngresada < 1 || opcionIngresada > 4);
        sc.nextLine();
        return opcionIngresada;
    }

    //metodo para crear ticket
    private TicketSoporte crearTicket() {
        Usuario usuario;
        System.out.println("\n=== Crear Ticket ===");
        System.out.println("Datos de ticket");
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();
        Date fechaDeCreacion = ingresarFecha();
        System.out.println("\nTicket con Usuario?");
        TicketSoporte ticket;
        String respuesta;
        do {
            System.out.print("S/N: ");
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                usuario = opcionesUsuario();
                ticket = new TicketSoporte(descripcion, fechaDeCreacion, usuario);
            } else {
                ticket = new TicketSoporte(descripcion, fechaDeCreacion);
            }
            System.err.println("Ingrese una respuesta valida.");
        } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));
        return ticket;
    }
    //metodo para ingresar fecha actual
    private Date ingresarFecha(){
        //Formato para ingresar la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        sdf.setLenient(false);
        System.out.println("Ingrese una fecha (dd/MM/yyyy)");
        while(true) {
            try {
                System.out.print("Fecha:");
                String fechaStr = sc.nextLine();
                Date fecha = sdf.parse(fechaStr);
                sc.nextLine();
                return fecha;
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Formato correcto: dd/MM/yyyy");
            }
        }
    }
    //metodo para seleccionar un usario existente o agregar un usuario.
    private Usuario opcionesUsuario(){
        int opcionIngresada =0;
        System.out.println("Opciones Usuario");
        System.out.println("1- Ingresar Usuario." +
                "\n2- Usuario ya existente. ");
        Usuario usuario;
        do {
            try {
                System.out.print("Ingrese una opcion: ");
                opcionIngresada = sc.nextInt();

                if (opcionIngresada != 1 && opcionIngresada != 2){
                    System.err.println("Ingrese un número válido.");
                }
            }catch (Exception e){
                System.err.println("Ingrese un numero valido.");
                sc.nextLine();
            }
        }while(opcionIngresada != 1 && opcionIngresada !=2);
        if(opcionIngresada == 1){
             usuario = agregarUsuario();
        }else{
             usuario = SeleccioanrUsuario();
        }
        return usuario;
    }
    //metodo para agregar usuario
    private Usuario agregarUsuario(){
        System.out.println("\nAgregar Usuario");
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("\nGmail: ");
        String gmail = sc.nextLine();
        Usuario usuario = new Usuario(nombre,gmail);
        listaUsuarios.add(usuario);
        System.out.println("Usuario agregado.");
        return usuario;
    }
    //metodo para mostrar usuarios
    private void mostrarUsuarios(){
        System.out.println("Usuarios Cargados:");
        for (Usuario usuario : listaUsuarios) {
            usuario.mostrarInfo();
        }
    }
    // Metodo para seleccionar usuario existente
    private Usuario SeleccioanrUsuario(){
        int idUsuario;
        mostrarUsuarios();
        sc.nextLine();
        while (true) {
            try {
                System.out.print("Ingrese el id del usuario: ");
                idUsuario = sc.nextInt();
                for (Usuario usuario : listaUsuarios) {
                    if (usuario.getIdUsuario() == idUsuario) {
                        return usuario;
                    }
                }
                System.err.println("Usuario no encontrado.");

            } catch (Exception e) {
                System.err.println("Ingrese un id valido.");
            }
        }
    }

    private void mostrarTickets(){
        for (TicketSoporte ticket : tickets) {
            ticket.mostrarDestalle();
        }
    }

    private TicketSoporte seleccionarTicket(){
        int idTicket;
        System.out.println("Ingrese el ID del Ticket");
        while(true) {
            try {
                System.out.print("ID: ");
                idTicket = sc.nextInt();
                for (TicketSoporte ticket : tickets) {
                    if (idTicket == ticket.getTicketId()) {
                        return ticket;
                    }
                }
                System.out.println("Ticket no encontrado.");
            }catch(Exception e){
                System.err.println("Ingrese un id valido.");
            }finally {
                sc.nextLine();
            }
        }
    }

    private void asignarTecnico( ){
        System.out.println("Asignar Tecnico");
        TicketSoporte ticket = seleccionarTicket();
        Tecnico tecnico = opcionesTecnico();
        ticket.setTecnico(tecnico);
        System.out.println("Tecnico asignado.");
        ticket.mostrarDestalle();
        enterParaContinuar();
    }
    private Tecnico opcionesTecnico(){
        System.out.println("Opciones Tecnico");
        System.out.println("1- Agregar Tecnico." +
                "\n2- Tecnico ya existente. ");
        Tecnico tecnico;
        int opcionIngresada =0;
        do {
            try {
                System.out.print("Ingrese una opcion: ");
                opcionIngresada = sc.nextInt();
            } catch (Exception e) {
               System.err.println("Ingrese un numero valido.");
            }
        }while (opcionIngresada != 1 && opcionIngresada != 2);
        if (opcionIngresada == 1){
             tecnico = agregarTecnico();
        }else {
            tecnico = seleccionarTecnico();
        }
        return  tecnico;
    }
    private void mostrarTecnicos(){
        System.out.println("================ Tecnicos ================:");
        for (Tecnico tecnico : tecnicos) {
            tecnico.mostrarDestalle();
        }
        System.out.println("====== ======== == ======== ======:");
        enterParaContinuar();

    }
    private Tecnico seleccionarTecnico(){
        int idTecnico;
        mostrarTecnicos();
        System.out.println("Ingrese el id del tecnico: ");
        while(true) {
            try{
                System.out.print("Id: ");
                idTecnico = sc.nextInt();
                for (Tecnico tecnico : tecnicos){
                    if (idTecnico == tecnico.getId()){
                        sc.nextLine();
                        return tecnico;
                    }
                }
                System.err.println("Tecnico no encontrado.");
            }catch (Exception e){
                System.err.println("Ingrese un id valido.");
            }finally {
                sc.nextLine();
            }
        }
    }
    //meetodo para agregar técnico
    private Tecnico agregarTecnico(){

        System.out.println("===== Agregar Tecnico ======");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("\nEspecialidad: ");
        String especialidad = sc.nextLine();
        Tecnico tecnico = new Tecnico(nombre,especialidad);
        tecnicos.add(tecnico);
        System.out.println("Tecnico agregado.");
        enterParaContinuar();
        return tecnico;

    }



    private void enterParaContinuar(){
        System.out.println("Presione Enter para continuar...");
        sc.nextLine();
        System.out.println("\n\n\n\n");
    }



}
