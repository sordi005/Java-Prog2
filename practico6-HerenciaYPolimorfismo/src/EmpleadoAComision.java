public class EmpleadoAComision extends Empleado {
    private double salarioMinimo;
    private int numeroClientesCaptados;
    private double montoPorCliente;

    public EmpleadoAComision(String dni, String nombre, String apellido, int anioIngreso, double salarioMinimo, int numeroClientesCaptados, double montoPorCliente) {
        super(dni, nombre, apellido, anioIngreso);
        this.salarioMinimo = salarioMinimo;
        this.numeroClientesCaptados = numeroClientesCaptados;
        this.montoPorCliente = montoPorCliente;
    }

    public int getNumeroCLientesCaptados(){
        return numeroClientesCaptados;
    }

    @Override
    public double getSalario(){
        double salario = montoPorCliente * numeroClientesCaptados;
        if (salario < salarioMinimo) {
            return salarioMinimo;
        }
        return salario;
    }
}
