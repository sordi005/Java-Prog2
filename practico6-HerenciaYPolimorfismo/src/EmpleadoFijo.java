public class EmpleadoFijo extends Empleado {
    private double sueldoBasico;
    private static final double PORC1 = 0.05;
    private static final double PORC2 = 0.1;
    private static final int ANIO1 = 2;
    private static final int ANIO2 = 5;

    public EmpleadoFijo(String dni, String nombre, String apellido, int anioIngreso, double sueldoBasico) {
        super(dni, nombre, apellido, anioIngreso);
        this.sueldoBasico = sueldoBasico;
    }

    private double porcAdicional() {
        int ant = antiguedadAnios();
        double porc = 0;
        if (ant > ANIO2) {
            porc = PORC2;
        } else if (ant >= ANIO1) {
            porc = PORC1;
        }
        return porc;
    }

    @Override
    public double getSalario() {
        return sueldoBasico + sueldoBasico * porcAdicional();
    }
}
