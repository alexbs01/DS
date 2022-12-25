package e1;

public class ShoppingCart implements StateOrder {
    private static final ShoppingCart instance = new ShoppingCart();
    private ShoppingCart(){}
    public static ShoppingCart getInstace() {
        return instance;
    }

    @Override
    public StateOrder nextState(Order order) {
        order.getLog().add("Order " + order.getId() + ": CheckOut Phase");
        return CheckOut.getInstace();
    }

    @Override
    public StateOrder previousState(Order order) {
        return ShoppingCart.getInstace();
    }

    @Override
    public String screenInfo(Order order) {

        return "* Al iniciar un nuevo pedido\n" +
               "Ejemplo: Order Number: " + order.getId() +
               "\nPhase: Shopping -- Welcome to online shop";
    }

    @Override
    public String getNameState() {
        return "Shopping Cart";
    }

}
