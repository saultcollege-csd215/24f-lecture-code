package java._04_stratified_design.megamart.stratified_with_abstractionbarrier;

/*
 *  NOTE:
 *  We can now use cart objects without having to know that the cart is implemented as a list of CartItems.
 *  With a good abstraction barrier, the implementation of the cart can be completely changed without effecting
 *  code that uses the abstraction barrier layer!
 *
 */
public class CartRules {

    public static boolean getsFreeShipping(Cart cart) {
        return cart.calcTotal() >= 20.0;
    }

    // NOTE: Compare this method with the addFreeTieClip in the 'needswork' package.
    // For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
    public static Cart addFreeTieClip(Cart cart) {
        // Using our new isNameInCart method to simplify finding the items
        var hasTie = cart.isNameInCart("tie");
        var hasTieClip = cart.isNameInCart("tie clip");

        if ( hasTie && !hasTieClip ) {
            return cart.addItem(new CartItem("tie clip", 0.0));
        }
        return cart;
    }
}
