package e1;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private HashMap<String, Integer> store = new HashMap<String, Integer>();

    public Store(HashMap<String, Integer> store) {
        this.store = store;
    }

    public HashMap<String, Integer> getStore() {
        return store;
    }
}
