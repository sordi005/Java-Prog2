public class Tecnico {
    private int tecnicoId;
    private  static int contadorTecnicos = 1;
    private String nombre;
    private String especialidad;

    public Tecnico(String nombre, String especialidad) {
        this.tecnicoId = contadorTecnicos++;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "tecnicoId=" + tecnicoId +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
    public String getNombre() {
        return nombre;
    }
    public void mostrarDestalle() {
        System.out.println(
        "\n=== DETALLE DEL TECNICO ==="
        + "\nId: " + tecnicoId
        + "\nNombre: " + nombre
        + "\nEspecialidad: " + especialidad
        + "\n=================================="
        );
    }
    public int getId() {
        return tecnicoId;
    }

}
