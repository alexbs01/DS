package e1;

import java.time.format.DateTimeFormatter;

public class Payment implements StateOrder{
    private static final Payment instance = new Payment();
    private Payment(){}
    public static Payment getInstace() {
        return instance;
    }

    @Override
    public StateOrder nextState(Order order) {

        if(order.isCancelled()) {
            order.getLog().add("Order " + order.getId() + ": Cancelled Phase");
            return Cancelled.getInstace();
        }

        order.getLog().add("Order " + order.getId() + ": Completed Phase");
        return Completed.getInstace();

    }

    @Override
    public StateOrder previousState(Order order) {
        return Payment.getInstace();
    }

    @Override
    public String screenInfo(Order order) {

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = order.getDateTime().format(dateTimeFormat);

        return "* Payment order: número de productos del carrito, hora del pedido\n" +
               "Ejemplo: Order Number: " + order.getId() +
               "\nPhase: Paid order: " + order.getProducts().size() + " products -- date " + dateTime;
    }

    @Override
    public String getNameState() {
        return "Payment";
    }
}