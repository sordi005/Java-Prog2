import java.util.Comparator;

public class comparadorPedidosPorFecha implements Comparator<Pedido> {
    @Override
    public int compare(Pedido o1,Pedido o2){
        return o1.getFecha().compareTo(o2.getFecha());
    }

}
