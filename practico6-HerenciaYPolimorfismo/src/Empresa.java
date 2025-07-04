import java.util.ArrayList;

public class Empresa {
    private ArrayList<Empleado> empleados;

    public Empresa(){
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado e){
        this.empleados.add(e);
    }

    public void mostrarSalarios(){
        for(Empleado emp : empleados){
            System.out.println(emp.nombreCompleto() + ": $" + emp.getSalario());
        }
    }

    public Empleado empleadoConMasClientes() {
        int max = -1;
        Empleado elMayor = null;
        for (Empleado e : empleados) {
            if (e instanceof EmpleadoAComision) {
                EmpleadoAComision eac = (EmpleadoAComision) e;
                int cant = eac.getNumeroCLientesCaptados();
                if (cant > max) {
                    max = cant;
                    elMayor = eac;
                }
            }
        }
        return elMayor;
    }
}
