package java._04_stratified_design.megamart.stratified;

import java.util.List;

// This layer sits 'one above' the Cart layer.
// Whereas the Cart layer is concerned with basic operations on the cart, this layer is concerned with
// more complex calculations that involve multiple items in the cart.
public class CartRules {

    public static boolean getsFreeShipping(List<CartItem> cart) {
        return Cart.calcTotal(cart) >= 20.0;
    }

    // NOTE: Compare this method with the addFreeTieClip in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public static List<CartItem> addFreeTieClip(List<CartItem> cart) {
        // Using our new isNameInCart method to simplify finding the items
        var hasTie = Cart.isNameInCart(cart, "tie");
        var hasTieClip = Cart.isNameInCart(cart, "tie clip");

        if ( hasTie && !hasTieClip ) {
            return Cart.addItem(cart, new CartItem("tie clip", 0.0));
        }
        return cart;
    }
}
