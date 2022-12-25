package e1;

public class Completed implements StateOrder{
    private static final Completed instance = new Completed();
    private Completed(){}
    public static Completed getInstace() {
        return instance;
    }

    @Override
    public StateOrder nextState(Order order) {
        return Completed.getInstace();
    }

    @Override
    public StateOrder previousState(Order order) {
        return Completed.getInstace();
    }

    @Override
    public String screenInfo(Order order) {
        return "* Completed order: número de productos en el carrito\n" +
               "Ejemplo: Order Number: " + order.getId() +
               "\nPhase: Completed Order: " + order.getProducts().size() + " products";
    }

    @Override
    public String getNameState() {
        return "Completed";
    }
}
