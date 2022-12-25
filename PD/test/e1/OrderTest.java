package e1;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    HashMap<String, Integer> productos = new HashMap<>();
    HashMap<String, Integer> vacios = new HashMap<>();
    LinkedList<String> log = new LinkedList<>();
    Store store = new Store(productos);

    // order inicia en ShoppingCart con 6 productos en la cesta
    Order order1 = new Order("1111", productos, ShoppingCart.getInstace(), false, false, null, log);

    // order1 inicia en ShoppingCart con la cesta vacía
    Order order2 = new Order("2222", vacios, ShoppingCart.getInstace(), false, false, null, log);

    // order2 inicia en Payment con 6 productos en la cesta y el pedido ya pagado
    Order order3 = new Order("3333", productos, Payment.getInstace(), true, false, LocalDateTime.MIN, log);

    void addProductos() {
        productos.put("111",  5);
        productos.put("222", 10);
        productos.put("333", 15);
        productos.put("444", 20);
        productos.put("555", 25);
        productos.put("666", 30);
   }

    @Test
    void getStateOrder() {
        // Comprobamos que está en el estado inicial
        assertEquals(order1.getStateOrder(), ShoppingCart.getInstace());

        // Comprobamos que no podemos volver a un estado previo al inicial
        order1.setStateOrder(order1.getStateOrder().previousState(order1));
        assertEquals(order1.getStateOrder(), ShoppingCart.getInstace());

        // Cambiamos al siguiente estado
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertNotEquals(order1.getStateOrder(), ShoppingCart.getInstace());
        assertEquals(order1.getStateOrder(), CheckOut.getInstace());

        // Volvemos para el estado anterior para comprobar que en este paso se puede dar marcha atrás
        order1.setStateOrder(order1.getStateOrder().previousState(order1));
        assertEquals(order1.getStateOrder(), ShoppingCart.getInstace());
        assertNotEquals(order1.getStateOrder(), CheckOut.getInstace());

        // Avanzamos dos estados y comprobamos que estamos en Payment
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertNotEquals(order1.getStateOrder(), ShoppingCart.getInstace());
        assertNotEquals(order1.getStateOrder(), CheckOut.getInstace());
        assertEquals(order1.getStateOrder(), Payment.getInstace());

        // Intentamos volver para atrás, y comprobamos que no podemos desde Payment
        order1.setStateOrder(order1.getStateOrder().previousState(order1));
        assertNotEquals(order1.getStateOrder(), CheckOut.getInstace());
        assertEquals(order1.getStateOrder(), Payment.getInstace());

        // Comprobamos que podemos ir al Completed y que no podemos movernos de ahí
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertEquals(order1.getStateOrder(), Completed.getInstace());

        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertEquals(order1.getStateOrder(), Completed.getInstace());

        order1.setStateOrder(order1.getStateOrder().previousState(order1));
        assertEquals(order1.getStateOrder(), Completed.getInstace());
    }

    @Test
    void screenInfo() {
        addProductos();
        assertEquals("""
                                * Al iniciar un nuevo pedido
                                Ejemplo: Order Number: 1111
                                Phase: Shopping -- Welcome to online shop""", order1.screenInfo(order1));

        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertEquals("""
                                * CheckOut : número de productos en el carrito
                                Ejemplo: Order Number: 1111
                                Phase: Checkout: 6""", order1.screenInfo(order1));

        // Para el test del estado Payment lo hacemos con RegEx porque la fecha y el tiempo cambia en cada ejecución
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertLinesMatch(("""
                * Payment order: número de productos del carrito, hora del pedido
                Ejemplo: Order Number: 1111
                Phase: Paid order: 6 products -- date \\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                """).lines(), order1.screenInfo(order1).lines());

        // Pasamos al estado Cancelled, para ello marcamos como cancelado el pedido
        order1.setCancelled(true);
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertEquals("""
                                * Cancelled o Completed order
                                Ejemplo: Order Number: 1111
                                Phase: Cancelled Order""", order1.screenInfo(order1));

        // Volvemos al estado Payment para esta vez ir al estado Completed
        order1.setStateOrder(Payment.getInstace());
        order1.setCancelled(false);
        order1.setStateOrder(order1.getStateOrder().nextState(order1));
        assertEquals("""
                                * Completed order: número de productos en el carrito
                                Ejemplo: Order Number: 1111
                                Phase: Completed Order: 6 products""", order1.screenInfo(order1));
    }

    @Test
    void cancelledOrder() {
        addProductos();

        // Comprobamos que no podemos cancelar un pedido que no esté en el estado de Paying
        assertEquals("The order can't be cancelled for being in other state", order1.cancellOrder(store));
        order2.addProduct(store, new Product("111", 3));
        order2.addProduct(store, new Product("222", 9));

        // Comprobamos que los productos del carrito ya no están en el almacén
        assertEquals(2, store.getStore().get("111"));
        assertEquals(1, store.getStore().get("222"));

        // Vamos al estado Payment y cancelamos el pedido
        order2.setStateOrder(order2.getStateOrder().nextState(order2));
        order2.setStateOrder(order2.getStateOrder().nextState(order2));
        order2.getStateOrder().screenInfo(order2);
        assertEquals("Order number 2222 was Cancelled", order2.cancellOrder(store));

        // Comprobamos que al cancelar el pedido, los productos volvieron al almacén
        assertEquals(5, store.getStore().get("111"));
        assertEquals(10, store.getStore().get("222"));

        assertEquals("Order number 3333 can't be Cancelled", order3.cancellOrder(store));
    }

    @Test
    void addProducts() {
        // Comprobamos que está en el estado inicial, para añadir objetos al carrito
        addProductos();
        assertEquals(order2.getStateOrder(), ShoppingCart.getInstace());

        assertEquals("Product number 111 was added " +
                            "to the shopping cart number 2222. " +
                            "Total of these products: 2", order2.addProduct(store, new Product("111", 2)));

        // Miramos si ahora en almacén solo hay 3 items con el código 111
        assertEquals(3, store.getStore().get("111"));

        assertEquals("Product number 111 was added " +
                "to the shopping cart number 2222. " +
                "Total of these products: 5", order2.addProduct(store, new Product("111", 3)));

        assertEquals("The product/s can't be added", order2.addProduct(store, new Product("111", 5)));

    }

    @Test
    void deleteProducts() {
        addProductos();

        // Añadimos 3 items con el ID 111 a la cesta
        order2.addProduct(store, new Product("111", 3));

        // Comprobamos que se actualizó el almacén
        assertEquals(2, store.getStore().get("111"));

        // Borramos el producto de la cesta para que muestre el código de error
        assertEquals("Product number 111 was removed " +
                "to the shopping cart number 2222.", order2.deleteProduct(store, "111"));

        // Volvemos a mirar si los productos de la cesta se sumaron al almacén tras borrarlos
        assertEquals(5, store.getStore().get("111"));

        // Intentamos eliminar un producto que no tenemos en la lista
        assertEquals("The product/s can't be removed", order2.deleteProduct(store, "111"));
    }

    @Test
    void modifyProduct() {
        addProductos();

        // Añadimos 3 items con el ID 222 a la cesta
        order2.addProduct(store, new Product("222", 3));

        // Miramos que en el almacén solo quedan 7
        assertEquals(7, store.getStore().get("222"));

        // Modificamos el producto 222 para poner
        assertEquals("Product number 222 was modified " +
                "to the shopping cart number 2222. Total of these products: 4", order2.modifyProduct(store, new Product("222", 4)));

        assertEquals(6, store.getStore().get("222"));

        // Probamos que no podemos poner un número superior al del almacén
        assertEquals("The product/s can't be modified", order2.modifyProduct(store, new Product("222", 11)));
        assertEquals("The product/s can't be modified", order2.modifyProduct(store, new Product("222", -5)));
    }

    @Test
    void printLog() {
        addProductos();

        order2.addProduct(store, new Product("111", 2));
        order2.addProduct(store, new Product("222", 6));
        order2.addProduct(store, new Product("444", 12));
        order2.addProduct(store, new Product("333", 1));

        order2.setStateOrder(order2.getStateOrder().nextState(order2));
        order2.modifyProduct(store, new Product("333", 8));
        order2.deleteProduct(store, "222");

        order2.setStateOrder(order2.getStateOrder().nextState(order2));
        order2.setStateOrder(order2.getStateOrder().nextState(order2));

        assertEquals("""
                                Order 2222: Shopping Cart Phase
                                - Add: Item: 111 - Quantity: 2 -> Shopping Cart -- Products: 1
                                - Add: Item: 222 - Quantity: 6 -> Shopping Cart -- Products: 2
                                - Add: Item: 444 - Quantity: 12 -> Shopping Cart -- Products: 3
                                - Add: Item: 333 - Quantity: 1 -> Shopping Cart -- Products: 4
                                Order 2222: CheckOut Phase
                                - Modify: Item: 333 - Quantity: 8 -> Check Out -- Products: 4
                                - Remove: Item: 222 -> Check Out -- Products: 3
                                Order 2222: Payment Phase
                                Order 2222: Completed Phase
                                """, order2.printLog());
    }
}