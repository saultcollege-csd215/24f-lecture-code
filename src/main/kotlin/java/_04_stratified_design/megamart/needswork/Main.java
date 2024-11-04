package java._04_stratified_design.megamart.needswork;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<CartItem> shoppingCart = new ArrayList<>();

    // LAYER: DOM operations
    public static void addItemToCart(String name, Double price) {
        shoppingCart = addItem(shoppingCart, new CartItem(name, price));    // Updating/reading a global variable is an action
        var total = calcTotal(shoppingCart);
        DOM.setCartTotal(total);                    // Updating the DOM is an action
        updateShippingIcons(shoppingCart);          // This is still an action, but it now uses an explicit input
        updateTaxDom(total);
    }

    // LAYER: Business logic (cart)
    public static List<CartItem> addItem(List<CartItem> items, CartItem item) {
        var newItems = new ArrayList<>(items);
        newItems.add(item);
        return newItems;
    }

    // LAYER: Business logic (cart)
    // TODO: This function could be simplified if we had a getItemByName function in the 'cart' layer
    public static List<CartItem> removeItemByName(List<CartItem> cart, String name) {
        var copy = new ArrayList<CartItem>();
        for ( var item : cart ) {
            // TODO: Here's that lookup-by-name pattern again!
            if (!item.name().equals(name)) {  // Copy all items except the one we want to remove
                copy.add(item);
            }
        }
        return copy;
    }

    // LAYER: DOM operations
    public static void deleteHandler(String name) {
        shoppingCart = removeItemByName(shoppingCart, name);

        var total = calcTotal(shoppingCart);
        DOM.setCartTotal(total);
        updateShippingIcons(shoppingCart);
        updateTaxDom(total);
    }

    // LAYER: DOM operations
    public static void updateShippingIcons(List<CartItem> cart) {

        var buyButtons = deepCopyBuyButtons(DOM.getBuyButtons());

        for (var button : buyButtons) {
            if (getsFreeShipping(addItem(cart, button.item()))) {
                button.showFreeShippingIcon();
            } else {
                button.hideFreeShippingIcon();
            }
        }
    }

    // LAYER: DOM operations
    public static void updateTaxDom(double shoppingCartTotal) {
        DOM.setTax(calcTax(shoppingCartTotal));
    }

    // LAYER: Business logic (general)
    public static double calcTax(double amount) {
        return amount * 0.13;
    }

    // LAYER: Business logic (cart rules)
    public static boolean getsFreeShipping(List<CartItem> cart) {
        return calcTotal(cart) >= 20.0;
    }

    // LAYER: Business logic (cart)
    public static double calcTotal(List<CartItem> items) {
        var total = 0.0;
        for (CartItem item : items) {
            total += item.price();
        }
        return total;
    }

    // LAYER: Business logic (cart)
    public static List<CartItem> deepCopyCart(List<CartItem> cart) {
        var copy = new ArrayList<CartItem>();
        for ( var item : cart ) {
            copy.add(new CartItem(item.name(), item.price()));
        }
        return copy;
    }

    // TODO: This function fits better as part of the BuyButton class
    // (i.e. It makes more sense for the BuyButton class to 'know' how to do a deep copy of a list of BuyButtons)
    public static List<BuyButton> deepCopyBuyButtons(List<BuyButton> buttons) {
        var copy = new ArrayList<>(buttons);
        for ( var button : buttons ) {
            copy.add(new BuyButton(button.item()));
        }
        return copy;
    }

    // LAYER: Business logic (cart rule)
    public static List<CartItem> addFreeTieClip(List<CartItem> cart) {

        // TODO: After adding the 'isInCart' function to our 'cart operations' layer, this code can be simplified!
        var hasTie = false;
        var hasTieClip = false;

        // TODO: Note this pattern of looping through the cart items and finding an item by name; it shows up in several places.
        //      This is an opportunity for encapsulation and generalization!
        for ( var item : cart ) {
            if ( item.name().equals("tie") ) {
                hasTie = true;
            } else if ( item.name().equals("tie clip") ) {
                hasTieClip = true;
            }
        }

        if ( hasTie && !hasTieClip ) {
            return addItem(cart, new CartItem("tie clip", 0.0));
        }
        return cart;
    }

    // LAYER: Business logic (cart)
    public static List<CartItem> setPriceByName(List<CartItem> cart, String name, double price) {
        var copy = new ArrayList<CartItem>(cart);
        // TODO: And here it is again in a similar form, but slightly different than the above examples
        for ( int i = 0; i < copy.size(); i += 1 ) {
            if ( copy.get(i).name().equals(name) ) {
                copy.set(i, setPrice(copy.get(i), price));
            }
        }
        return copy;
    }

    // LAYER: Business logic (cart item)
    public static CartItem setPrice(CartItem item, double price) {
        // TODO: make this a 'wither' method on CartItem
        return new CartItem(item.name(), price);
    }

    // TODO: If we had a 'isNameInCart' function in the Cart layer, some of our code in higher layers can be simplified!

    // TODO: If we had a 'getItemByName' function, some of our code above could be simplified!

    // TODO: If we had a 'updateItem' function in this layer, the setPriceByName function can be simplified!


}
