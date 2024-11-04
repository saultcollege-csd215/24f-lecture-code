package java._04_stratified_design.megamart.stratified_with_abstractionbarrier;

import java.util.HashMap;
import java.util.Map;

/* NOTE:
 *
 * This layer is now an abstraction barrier.
 * Code above here will not need to change, even if the implementation of data in this layer changes completely.
 * We could change List<CartItem> to a Map<String, CartItem> and the CartRules layer above does not need to change!
 *
 * (There are two Cart classes implemented here; one using List<CartItem> and one using Map<String, CartItem>.
 * Try changing the CartRules layer to use the Map<String, CartItem> version and see that it still works!)
 */

/*
public class Cart {  // LIST IMPLEMENTATION

    private List<CartItem> cartItems;


    public Cart() {
        this.cartItems = new ArrayList<CartItem>();
    }

    private Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart addItem(CartItem item) {
        return new Cart(ListUtils.add(cartItems, item));
    }

    public double calcTotal() {
        System.out.println("Calculating total...");
        var total = 0.0;
        for (CartItem item : cartItems) {
            total += item.price();
            System.out.println(item.price());
        }
        return total;
    }

    // NOTE: Compare this method with the setPriceByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public Cart setPriceByName(String name, double price) {
        // Using our new getItemByName method to simplify finding the item
        var item = getItemByName(name);

        if ( item == null ) {
            return this;
        }

        // List operation is relegated to the low-level ListUtils layer
        // Updating a CartItem is relegated to the CartItem layer
        return new Cart(ListUtils.replace(cartItems, item, item.withPrice(price)));
    }


    // NOTE: Compare this method with the removeItemByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public Cart removeItemByName(String name) {
        var item = getItemByName(name);
        return item == null ? this : new Cart(ListUtils.remove(cartItems, item));
    }

    public boolean isNameInCart(String name) {
        return getItemByName(name) != null;
    }

    // This method encapsulates an operation that is used in many places; now we can reuse it simply by calling the method!
    public CartItem getItemByName(String name) {
        for ( var item : cartItems ) {
            if ( item.name().equals(name) ) {
                return item;
            }
        }
        return null;
    }
}
*/

public class Cart {  // MAP IMPLEMENTATION
    private Map<String, CartItem> cartItems;


    public Cart() {
        this.cartItems = new HashMap<String, CartItem>();
    }

    private Cart(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart addItem(CartItem item) {
        return new Cart(MapUtils.put(cartItems, item.name(), item));
    }

    public double calcTotal() {
        var total = 0.0;
        for (CartItem item : cartItems.values()) {
            total += item.price();
        }
        return total;
    }

    // NOTE: Compare this method with the setPriceByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public Cart setPriceByName(String name, double price) {
        if ( cartItems.containsKey("name") ) {
            var item = cartItems.get(name);
            // List operation is relegated to the low-level ListUtils layer
            // Updating a CartItem is relegated to the CartItem layer
            return new Cart(MapUtils.put(cartItems, item.name(), item.withPrice(price)));
        }
        return this;
    }


    // NOTE: Compare this method with the removeItemByName in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public Cart removeItemByName(String name) {
        return new Cart(MapUtils.remove(cartItems, name));
    }

    public boolean isNameInCart(String name) {
        // return getItemByName(name) != null;
        return cartItems.containsKey(name);
    }

    // This method encapsulates an operation that is used in many places; now we can reuse it simply by calling the method!
//    public CartItem getItemByName(String name) {
//        return cartItems.get(name);
//    }
}