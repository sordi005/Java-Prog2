
public class Empleado {
    private int empledadoID;
    private String nombre;
    private String puesto;
    private Double salario;
    private static int totalEmpleados = 0;


    public Empleado(int empledadoID, String nombre, String puesto, Double salario) {

        this.empledadoID = empledadoID;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Empleado(String nombre, String puesto) {
        this.empledadoID = totalEmpleados;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 3000.0;
        totalEmpleados++;


    }

    @Override
    public String toString() {
        return "Empleado{" +
                "empledadoID=" + empledadoID +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                '}';
    }

    public void actualizarSalario(int aumento) {
        salario += aumento;
        System.out.println("Epleado : " + nombre);
        System.out.println("Salario actualizado: " + salario);
    }

    public void actualizarSalario() {
        int porcentajeAumento = 15;
        salario += (1 + porcentajeAumento / 100.0);
        System.out.println("Epleado : " + nombre);
        System.out.println("Salario aumentado un 15: " + salario + " (valor por defecto)");
    }

    public static int mostrarTotalEmpleados() {
        return totalEmpleados;
    }
}

