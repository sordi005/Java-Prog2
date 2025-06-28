public static void main(String[] args) {
    Empresa emp = new Empresa();

    emp.agregarEmpleado(new EmpleadoFijo("44582361", "Ana", "López", 2024, 45000));
    emp.agregarEmpleado(new EmpleadoAComision("27584006", "Pepe", "Gómez", 2010, 300000, 420, 2000));
    emp.agregarEmpleado(new EmpleadoFijo("35632481", "Luis", "Gámez", 2019, 50000));
    emp.agregarEmpleado(new EmpleadoAComision("40754852", "Leo", "Torres", 2017, 90000, 150, 400));
    emp.agregarEmpleado(new EmpleadoFijo("22475840", "María", "Pérez", 2000, 100000));

    emp.mostrarSalarios();

    System.out.println("El empleado con más clientes fue: " + emp.empleadoConMasClientes().nombreCompleto());
}