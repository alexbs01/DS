package e1;

public class Cancelled implements StateOrder{
    private static final Cancelled instance = new Cancelled();
    private Cancelled(){}
    public static Cancelled getInstace() {
        return instance;
    }

    @Override
    public StateOrder nextState(Order order) {
        return Cancelled.getInstace();
    }

    @Override
    public StateOrder previousState(Order order) {
        return Cancelled.getInstace();
    }

    @Override
    public String screenInfo(Order order) {
        return "* Cancelled o Completed order\n" +
               "Ejemplo: Order Number: " + order.getId() +
               "\nPhase: Cancelled Order";
    }

    @Override
    public String getNameState() {
        return "Cancelled";
    }
}
