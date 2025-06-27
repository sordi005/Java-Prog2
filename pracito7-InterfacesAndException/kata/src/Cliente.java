class Cliente implements Notificable {
    private String nombre;
    private String email;

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    @Override
    public void notificar(String mensaje) {
        System.out.println(mensaje);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Paypal implements PagoConDescuento {
        String email;
        public Paypal(String email) {
            this.email = email;
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Cliente.Paypal{" +
                    "email='" + email + '\'' +
                    '}';
        }

        @Override
        public void procesarPago(double monto) {
            System.out.println("Procesando pago con PayPal:");
            System.out.println("Email: " + email);
            System.out.println("Monto: $" + monto);
        }

        @Override
        public void aplciarDescuento(double porcentaje) {
            System.out.println("Descuento del " + porcentaje + "% aplicado a PayPal");
        }
    }

    public static interface Notificable {
        public void notificar(String mensaje);

    }
}
