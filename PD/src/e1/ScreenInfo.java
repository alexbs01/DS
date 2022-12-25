package e1;

public interface ScreenInfo {

    /**
     * Muestra la información del pedido en función de en que estado estemos
     * @param order Pedido del que queremos mirar información
     * @return Un string con información relevante para el estado en el que se llama a esta función
     */
    String screenInfo(Order order);

    /**
     * Retona el nombre del estado en el que está
     * @return Un String con el nombre del estado
     */
    String getNameState();
}
