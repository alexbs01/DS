package e1;

import java.time.LocalDateTime;

public class CheckOut implements StateOrder{
    private static final CheckOut instance = new CheckOut();
    private CheckOut(){}
    public static CheckOut getInstace() {
        return instance;
    }

    @Override
    public StateOrder nextState(Order order) {
        payOrder(order);
        order.getLog().add("Order " + order.getId() + ": Payment Phase");

        return Payment.getInstace();
    }

    @Override
    public StateOrder previousState(Order order) {
        order.getLog().add("Order " + order.getId() + ": Shopping Cart Phase");

        return ShoppingCart.getInstace();
    }

    @Override
    public String screenInfo(Order order) {

        return "* CheckOut : número de productos en el carrito\n" +
               "Ejemplo: Order Number: " + order.getId() +
               "\nPhase: Checkout: " + order.getProducts().size();
    }

    /**
     * Establece el pedido como pagado y guarda la hora a la que se efectúa
     * @param order Pedido que hace el pago
     */
    public void payOrder(Order order) {
        order.setPayed(true);

        LocalDateTime dateTimeOfPay = LocalDateTime.now();
        order.setDateTime(dateTimeOfPay);
    }

    @Override
    public String getNameState() {
        return "Check Out";
    }
}
