package java._03_staying_immutable.deepCopy;

import java._03_staying_immutable.megaMart.givenCode.CartItem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        var L1 = List.of(new CartItem("A", 1.0), new CartItem("B", 2.0));

        var L2 = shallowCopy(L1);
        var L3 = stillAShallowCopy(L1);
        var L4 = deepCopy(L1);

        System.out.println("Reference equal: " + (L1.get(0) == L2.get(0)) + ", value equality: " + L1.equals(L2));
        System.out.println("Reference equal: " + (L1.get(0) == L3.get(0)) + ", value equality: " + L1.equals(L3));
        System.out.println("Reference equal: " + (L1.get(0) == L4.get(0)) + ", value equality: " + L1.equals(L4));
    }

    public static List<CartItem> shallowCopy(List<CartItem> original) {
        return List.copyOf(original);
    }

    public static List<CartItem> stillAShallowCopy(List<CartItem> original) {
        var copy = new ArrayList<CartItem>();
        for ( var item : original ) {
            copy.add(item);
        }
        return copy;
    }

    public static List<CartItem> deepCopy(List<CartItem> original) {
        var copy = new ArrayList<CartItem>();
        for ( var item : original ) {
            copy.add(new CartItem(item.name(), item.price()));
        }
        return copy;
    }
}
