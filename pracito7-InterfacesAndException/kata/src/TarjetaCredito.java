public class TarjetaCredito  implements PagoConDescuento {
    private String numeroTarjeta ;

    public TarjetaCredito(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Procesando pago con PayPal:");
        System.out.println("numero:: " + numeroTarjeta);
        System.out.println("Monto: $" + monto);
    }

    @Override
    public void aplciarDescuento(double porcentaje) {
        System.out.println("Descuento del " + porcentaje + "% aplicado a Tarjeta De Credito");

    }

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "numeroTarjeta='" + numeroTarjeta + '\'' +
                '}';
    }

}
