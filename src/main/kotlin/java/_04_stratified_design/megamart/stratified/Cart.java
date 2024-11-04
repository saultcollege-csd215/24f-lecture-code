package java._04_stratified_design.megamart.stratified;

import java.util.List;

// NOTE: This class contains only code that works at the "Cart" level
//       Code in here only depends on 'lower' layers like the CartItem (and not DOM)
//       Code that operates at the "CartItem" level is relegated to the CartItem layer
public class Cart {

    public static List<CartItem> addItem(List<CartItem> items, CartItem item) {
        return ListUtils.add(items, item);
    }

    public static double calcTotal(List<CartItem> items) {
        var total = 0.0;
        for (CartItem item : items) {
            total += item.price();
        }
        return total;
    }

    // NOTE: Compare this method with the setPriceByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public static List<CartItem> setPriceByName(List<CartItem> cart, String name, double price) {
        // Using our new getItemByName method to simplify finding the item
        var item = getItemByName(cart, name);

        if ( item == null ) {
            return cart;
        }

        // List operation is relegated to the low-level ListUtils layer
        // Updating a CartItem is relegated to the CartItem layer
        return ListUtils.replace(cart, item, item.withPrice(price));
    }


    // NOTE: Compare this method with the removeItemByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public static List<CartItem> removeItemByName(List<CartItem> cart, String name) {
        var item = getItemByName(cart, name);
        return item == null ? cart : ListUtils.remove(cart, item);
    }

    public static boolean isNameInCart(List<CartItem> cart, String name) {
        // At first, we might write this function like this:
//        for ( var item : cart ) {
//            if ( item.name().equals(name) ) {
//                return true;
//            }
//        }
//        return false;


        // But after writing getItemByName, we can recognize that lookup-by-name pattern again
        // and reuse it to perform the necessary calculation here!
        return getItemByName(cart, name) != null;
    }

    // This method encapsulates an operation that is used in many places; now we can reuse it simply by calling the method!
    public static CartItem getItemByName(List<CartItem> cart, String name) {
        for ( var item : cart ) {
            if ( item.name().equals(name) ) {
                return item;
            }
        }
        return null;
    }
}
