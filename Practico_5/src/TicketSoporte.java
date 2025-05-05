import java.util.Date;

public class TicketSoporte {
    private static int contadorTickets = 1;
    private int ticketId;
    private String descripcion;
    private Date fechaCreacion;
    private enum Estados{
        abierto,en_proceso,cerrado
    };
    private Estados estado;
    private Usuario usuario;
    private Tecnico tecnico;

    //metodo contructor
    public TicketSoporte(String descripcion, Date fechaCreacion) {
        this.ticketId = contadorTickets++;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = Estados.abierto;
    }
    public TicketSoporte(String descripcion, Date fechaCreacion, Usuario usuario) {
        this.ticketId = contadorTickets++;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this .estado = Estados.abierto;
        this.usuario = usuario;
    }
    public TicketSoporte(String descripcion, Date fechaCreacion, Usuario usuario, Tecnico tecnico) {}
    @Override

    public String toString() {
        return "TicketSoporte{" +
                "ticketId=" + ticketId +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                ", usuario=" + usuario +
                '}';
    }

    public int getTicketId() {
        return ticketId;
    }
    public void mostrarDestalle(){
        System.out.println(
          "\n=== DETALLE DEL TICKET ==="
        + "\nTicket Id: " + ticketId
        + "\nDescripcion: " + descripcion
        + "\nFecha: " + fechaCreacion
        + "\nEstado: " + estado
        + "\nUsuario: " + (usuario != null ? usuario.getNombre() : "Sin asignar")
        +"\nTecnico: "+(tecnico != null? tecnico.getNombre() : "Sin asignar")
        + "\n--------------------------------");}
    //metodo para cerrar ticket
    public void  cerrarTicket(){
        if (estado != Estados.cerrado) {
            this.estado = Estados.cerrado;
            System.out.println("ticket cerrado");
        }else System.out.println("El estado del ticket ya esta cerrado.");
    }
    public void setTecnico(Tecnico tecnico){
        this.tecnico = tecnico;
    }

}
