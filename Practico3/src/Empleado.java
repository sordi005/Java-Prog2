
public class Empleado {
    private int empledadoID;
    private String nombre;
    private String puesto;
    private Double salario;
    private static int totalEmpleados;
    private static int contadorID;


    public Empleado(int empledadoID, String nombre, String puesto, Double salario) {

        this.empledadoID = empledadoID;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;
        contadorID++;
    }

    public Empleado(String nombre, String puesto) {
        this.empledadoID = ++contadorID;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 3000.0;
        totalEmpleados++;
        contadorID++;

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

    public void actualizarSalario(int aumento){
            salario += aumento;
            System.out.println("Epleado : "+  nombre );
            System.out.println("Salario actualizado: " + salario);
    }
    public void actualizarSalario(){
        int aumento = 500;
        salario += aumento;
        System.out.println("Epleado : "+  nombre );
        System.out.println("Salario actualizado: " + salario+ "(valor por defecto)");
    }
    public static int mostrarTotalEmpleados(){
        return totalEmpleados;
    }


    public static void main(String[] args) {

        Empleado empleado1 = new Empleado(1,"Santiago","Administrador",3000.0);
        Empleado empleado2= new Empleado("Pedro","Encargado");
        Empleado empleado3 = new Empleado(3,"Ricardo","vendedor",3500.0);

        System.out.println(empleado1+ "\n");
        System.out.println(empleado2+ "\n");
        System.out.println(empleado3+ "\n");


        empleado1.actualizarSalario(700);
        System.out.println("\n");
        empleado2.actualizarSalario();
        System.out.println("\n");

        int totalEmpleados = Empleado.mostrarTotalEmpleados();
        System.out.println("Total empleados: " + totalEmpleados);

        System.out.println(empleado1+ "\n");
        System.out.println(empleado2+ "\n");
        System.out.println(empleado3+ "\n");




    }
}