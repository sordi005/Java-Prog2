public class testDeVerificacion{

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
