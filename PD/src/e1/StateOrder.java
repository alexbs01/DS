package e1;

public interface StateOrder extends ScreenInfo{
    /**
     * Pasa al siguiente estado
     * @param order Pedido al cual queremos hacer pasar al siguiente estado
     * @return ShoppingCart -> CheckOut -> Payment -> (Cancelled XOR Completed)
     */
    StateOrder nextState(Order order);

    /**
     * Pasa al estado anterior
     * @param order Pedido al cual queremos hacer pasar al estado anterior
     * @return Cancelled, Completed Payment, Checkout -> ShoppingCart
     */
    StateOrder previousState(Order order);

}
