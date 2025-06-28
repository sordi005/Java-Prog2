public class Usuario {
    private static int contadorUsuarios = 1;
    private  int idUsuario;
    private String nombre;
    private String email;

    public Usuario(String nombre, String gmail) {
        this.idUsuario = contadorUsuarios++;
        this.nombre = nombre;
        this.email = gmail;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public void mostrarInfo(){
        System.out.println(
                "========================"
                +"Usuario id: "+idUsuario
                +"\n Nombre: "+nombre
                +"\nEmail: "+email
                +"\n====================");

    }

    public String getNombre() {
        return nombre;
    }
    public String getGmail() {
        return email;
    }
    public int getIdUsuario() {
        return idUsuario;
    }

}
