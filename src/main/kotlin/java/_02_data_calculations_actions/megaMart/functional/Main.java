package java._02_data_calculations_actions.megaMart.functional;

import java._02_data_calculations_actions.megaMart.givenCode.CartItem;
import java._02_data_calculations_actions.megaMart.givenCode.DOM;

import java.util.ArrayList;
import java.util.List;

import static java._02_data_calculations_actions.megaMart.givenCode.App.shoppingCart;
// After refactoring with calculation, the following import is no longer needed:
// import static _02_data_calculations_actions.megaMart.givenCode.App.shoppingCartTotal;


public class Main {

    public static List<CartItem> addItemToCart(List<CartItem> cart, String name, Double price) {
        // Instead of updating a global variable, we now pass in the cart as an input
        // and return a cart as an explicit output
        cart = addItem(cart, new CartItem(name, price));    // Updating/reading a global variable is an action
        calcCartTotal(cart);     // This is still an action, but it now uses an explicit input instead of reading a global                                              // The lines below fit better in this function than as a separate function

        return cart;
    }

    // This new function is a calculation, and thus easily testable and reusable
    // (We now use addItem in a couple places: addItemToCart, and in updateShippingIcons to test for free shipping)
    public static List<CartItem> addItem(List<CartItem> items, CartItem item) {
        var newItems = new ArrayList<>(items);
        newItems.add(item);
        return newItems;
    }

    public static void calcCartTotal(List<CartItem> cart) {
        var total = calcTotal(cart);
        DOM.setCartTotal(total);                    // Updating the DOM is an action
        updateShippingIcons(cart);          // This is still an action, but it now uses an explicit input
        updateTaxDom(total);
    }

    public static void updateShippingIcons(List<CartItem> cart) {
        var buyButtons = DOM.getBuyButtons();   // Reading the DOM is an action

        for (var button : buyButtons) {
            // There is a business rule embedded here (carts >= 20 get free shipping)
            // This code also requires the shoppingCartTotal to be initialized before testing
            if (getsFreeShipping(addItem(cart, button.item()))) {
                // Can't test this code without a mock DOM
                button.showFreeShippingIcon();      // Updating the DOM is an action
            } else {
                button.hideFreeShippingIcon();      // Updating the DOM is an action
            }
        }
    }

    public static void updateTaxDom(double shoppingCartTotal) {
        // By passing in the total we now have no external inputs
        // var tax = shoppingCartTotal * 0.13;  // Reading a global variable is an action
        DOM.setTax(calcTax(shoppingCartTotal));                     // Updating the DOM is still an action
    }

    // These are business rules encapsulated as calculations.
    // It is often useful to place business rule calculations like this into pure functions
    // so they may be easily reused.
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

        // Or, a few equivalent one-liners using streams:
//        return items.stream().map( item -> item.price() ).reduce(0.0, (a, b) -> a + b);
//        return items.stream().map(CartItem::price).reduce(0.0, Double::sum);
//        return items.stream().mapToDouble(CartItem::price).sum();
    }


}

