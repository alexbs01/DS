package e1;

import java.time.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.String;

public class Order implements ScreenInfo {
    private String id;
    private HashMap<String, Integer> products;
    private StateOrder stateOrder;
    private boolean payed;
    private boolean cancelled;
    private LocalDateTime dateTime;
    private LinkedList<String> log;

    public Order(String id, HashMap<String, Integer> products, StateOrder stateOrder, boolean payed, boolean cancelled, LocalDateTime dateTime, LinkedList<String> log) {
        this.id = id;
        this.products = products;
        this.stateOrder = stateOrder;
        this.payed = payed;
        this.cancelled = cancelled;
        this.dateTime = dateTime;
        this.log = log;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public StateOrder getStateOrder() {
        return stateOrder;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LinkedList<String> getLog() {
        return log;
    }

    @Override
    public String getNameState() {
        return getStateOrder().getNameState();
    }

    // SETTERS
    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setStateOrder(StateOrder stateOrder) {
        this.stateOrder = stateOrder;
    }

    // MÉTODOS
    @Override
    public String screenInfo(Order order) {
        return getStateOrder().screenInfo(order);
    }

    public String cancellOrder(Store store) {

        // Si estamos en estado Payment
        if(stateOrder.equals(Payment.getInstace())) {
            // Calculamos cuanto tiempo pasó entre la fecha de pago y la actual para saber si se puede cancelar
            Duration date = Duration.between(this.getDateTime(), LocalDateTime.now());
            Duration limit = Duration.ofHours(24);

            if(date.getSeconds() < limit.getSeconds()) {
                this.setStateOrder(Cancelled.getInstace());
                this.setCancelled(true);
                this.getStateOrder().nextState(this);

                emptyShoppingCart(store);

                return "Order number " + this.getId() + " was Cancelled";

            } else {
                return "Order number " + this.getId() + " can't be Cancelled";

            }
        } else {
            return "The order can't be cancelled for being in other state";
        }
    }

    /**
     * Añadir un producto a la cesta se hará siempre y cuando haya existencias en el almacén,
     * si se añade a la cesta, implica que se restará la cantidad de ese producto del almacén
     *
     * @param store Almacén del que consulta si hay productos o no
     * @param products Producto a añadir en la cesta
     * @return Un String que da la información necesaria para saber si se añadió o no el producto
     */
    public String addProduct(Store store, Product products) {
        /* Si el producto existe en el almacén &&
           Hay MÁS productos en almacén de los que se piden &&
           Estamos en ShoppingCart
           */
        if(store.getStore().containsKey(products.getId()) &&
                store.getStore().get(products.getId()) >= products.getQuantity() &&
                this.getStateOrder() == ShoppingCart.getInstace()) {

            // Resta los productos del almacén
            store.getStore().replace(products.getId(), store.getStore().get(products.getId()) - products.getQuantity());


            // Si el producto ya está en la cesta suma el número de productos
            if(this.getProducts().containsKey(products.getId())) {
                // Sumamos el número de productos nuevos a la cesta
                this.getProducts().put(products.getId(), this.getProducts().get(products.getId()) + products.getQuantity());

                // Guardamos la operación hecha en el registro
                this.getLog().add("- Add: Item: " + products.getId() + " - Quantity: " + products.getQuantity() + " -> Shopping Cart -- Products: " + this.getProducts().size());

                return "Product number " + products.getId() +
                        " was added to the shopping cart number " + this.getId() +
                        ". Total of these products: " + this.getProducts().get(products.getId());

            // Si no lo está, añade el producto a la lista
            } else {
                // Lo añadimos a la lista
                this.getProducts().put(products.getId(), products.getQuantity());

                // Gurarmos la operación en el log
                this.getLog().add("- Add: Item: " + products.getId() + " - Quantity: " + products.getQuantity() + " -> Shopping Cart -- Products: " + this.getProducts().size());

                return "Product number " + products.getId() +
                        " was added to the shopping cart number " + this.getId() +
                        ". Total of these products: " + products.getQuantity();
            }
        }

        // Mensaje de error que se muestra cuando no hay suficientes productos en almacén o no estamos en ShoppingCart
        return "The product/s can't be added";
    }

    /**
     * Eliminar un producto de la lista lo elimina por completo y suma la cantidad que tenías del mismo
     * al almacén.
     *
     * @param store Almacén del que consulta si hay productos o no
     * @param productId Producto a eliminar de la cesta
     * @return Un String que da la información necesaria para saber si se eliminó o no el producto
     */
    public String deleteProduct(Store store, String productId) {
        /* Si el producto existe en el almacén &&
           Hay MÁS productos en almacén de los que se piden &&
           Estamos en ShoppingCart
           */
        if(this.getProducts().containsKey(productId) &&
                (this.getStateOrder() == ShoppingCart.getInstace() ||
                 this.getStateOrder() == CheckOut.getInstace())) {

            // Suma los productos de la cesta al almacén
            store.getStore().replace(productId, store.getStore().get(productId) + this.getProducts().get(productId));

            // Si el producto ya está en la cesta suma el número de productos
            this.getProducts().remove(productId);

            // Guardamos un mensaje en el log de que se eliminó un producto de la cesta
            this.getLog().add("- Remove: Item: " + productId + " -> " + this.getNameState() + " -- Products: " + this.getProducts().size());

            return "Product number " + productId +
                    " was removed to the shopping cart number " + this.getId() + ".";

        }

        // Mensaje de error que se muestra cuando no hay suficientes productos en almacén o no estamos en ShoppingCart
        return "The product/s can't be removed";
    }

    /**
     * Modificar un producto significa que en un producto de tu cesta, estableces una
     * cantidad determinada de ese item, siempre y cuando haya existencias de él en almacén.
     *
     * @param store Almacén del que consulta si hay productos o no
     * @param products Producto a modificar en la cesta
     * @return Un String que da la información necesaria para saber si se modificó o no el producto
     */
    public String modifyProduct(Store store, Product products) {
        /* Si el producto existe en el almacén &&
           El producto está en la cesta &&
           Hay MÁS productos en almacén de los que se piden &&
           Y ponemos una cantidad positiva de productos &&
           (Estamos en ShoppingCart || CheckOut)
           */
        if(store.getStore().containsKey(products.getId()) &&
                this.getProducts().containsKey(products.getId()) &&
                store.getStore().get(products.getId()) >= products.getQuantity() &&
                products.getQuantity() >= 0 &&
                (this.getStateOrder() == ShoppingCart.getInstace() ||
                    this.getStateOrder() == CheckOut.getInstace())) {

            // Resta los productos del almacén
            store.getStore().replace(products.getId(),
                    store.getStore().get(products.getId()) + this.getProducts().get(products.getId()) - products.getQuantity());

            this.getLog().add("- Modify: Item: " + products.getId() + " - Quantity: " + products.getQuantity() + " -> " + this.getNameState() + " -- Products: " + this.getProducts().size());

            // Modificamos la cantidad del producto que tenemos en la cesta, como si hicieramos un set
            this.getProducts().replace(products.getId(), products.getQuantity());
            return "Product number " + products.getId() +
                    " was modified to the shopping cart number " + this.getId() +
                    ". Total of these products: " + this.getProducts().get(products.getId());

        }

        // Mensaje de error que se muestra cuando no hay suficientes productos en almacén o no estamos en ShoppingCart
        return "The product/s can't be modified";
    }

    /**
     * Cuando un pedido se cancela, los productos de este se deben enviar de vuelta al almacén
     * esta función vacía las cestas de la compra y manda los productos al almacén
     * @param store Almacén al que queremos enviar los productos
     */
    private void emptyShoppingCart(Store store) {
        for(String productId : this.getProducts().keySet()) {
            // Suma la cantidad que había de cada producto al almacén
            store.getStore().replace(productId, store.getStore().get(productId) + this.getProducts().get(productId));
        }

    }

    /**
     * Muestra el log del pedido en cuestión
     * @return Muestra un registro del pedido en el que cada línea
     * es una acción (añadir, quitar, modificar o cambiar de estado).
     */
    public String printLog() {
        StringBuilder firstLine = new StringBuilder("Order " + this.getId() + ": Shopping Cart Phase\n");

        for(String logLine : this.getLog()) {
            firstLine.append(logLine).append("\n");
        }

        return String.valueOf(firstLine);
    }
}

