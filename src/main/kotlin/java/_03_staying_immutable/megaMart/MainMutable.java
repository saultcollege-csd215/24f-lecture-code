package java._03_staying_immutable.megaMart;

import java._03_staying_immutable.megaMart.givenCode.App;
import java._03_staying_immutable.megaMart.givenCode.CartItem;
import java._03_staying_immutable.megaMart.givenCode.DOM;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java._03_staying_immutable.megaMart.givenCode.App.shoppingCart;

public class MainMutable {

    /****************************
     * Most of the code below is as it was in the previous unit: _02_data_calculations_actions.megaMart.functional.Main
     *
     * New code that demonstrates points where there may be unsafe code is marked with a comment like this:
     * /*** UNSAFE ***/

    public static void addItemToCart(String name, Double price) {
        shoppingCart = addItem(shoppingCart, new CartItem(name, price));    // Updating/reading a global variable is an action
        // calcCartTotal();                                                 // The lines below fit better in this function than as a separate function
        var total = calcTotal(shoppingCart);
        DOM.setCartTotal(total);                    // Updating the DOM is an action
        updateShippingIcons(shoppingCart);          // This is still an action, but it now uses an explicit input
        updateTaxDom(total);

        /*** UNSAFE ***/
        // We have no control over this function, and it might change shoppingCart
        // We need to do some defensive copying!
        App.blackFridayPromotion(shoppingCart);

    }

    public static List<CartItem> addItem(List<CartItem> items, CartItem item) {
        var newItems = new ArrayList<>(items);
        newItems.add(item);
        return newItems;
    }

    /*** NEW ***/
    /*** UNSAFE ***/
    // This function is an action because it updates the given list in-place (an IMPLICIT OUTPUT)
    public static void removeItemByName(List<CartItem> cart, String name) {
        for ( var item : cart ) {
            if (item.name().equals(name)) {
                cart.remove(item);          // updating a list in-place is an IMPLICIT OUTPUT!
                return;
            }
        }
    }

    /*** NEW ***/
    public static void deleteHandler(String name) {
        /*** UNSAFE ***/
        // removeItemByName updates the shoppingCart list in-place (an IMPLICIT OUTPUT)
        removeItemByName(shoppingCart, name);


        var total = calcTotal(shoppingCart);   // This is fine because calcTotal is in the SAFE ZONE (it's a calculation)
        DOM.setCartTotal(total);
        updateShippingIcons(shoppingCart);     // This is fine because we made shoppingCart into an EXPLICIT INPUT last unit
                                               // updateShippingIcons will not change shoppingCart; it is in the SAFE ZONE
        updateTaxDom(total);
    }

    public static void updateShippingIcons(List<CartItem> cart) {

        /*** UNSAFE ***/
        // We are getting mutable data from an untrusted source!
        // The data referred to by buyButtons may change at any time!
        // We need to do some defensive copying!
        var buyButtons = DOM.getBuyButtons();

        for (var button : buyButtons) {
            if (getsFreeShipping(addItem(cart, button.item()))) {
                button.showFreeShippingIcon();
            } else {
                button.hideFreeShippingIcon();
            }
        }
    }

    public static void updateTaxDom(double shoppingCartTotal) {
        DOM.setTax(calcTax(shoppingCartTotal));
    }

    public static double calcTax(double amount) {
        return amount * 0.13;
    }

    public static boolean getsFreeShipping(List<CartItem> cart) {
        return calcTotal(cart) >= 20.0;
    }

    public static double calcTotal(List<CartItem> items) {
        var total = 0.0;
        for (CartItem item : items) {
            total += item.price();
        }
        return total;
    }


}

