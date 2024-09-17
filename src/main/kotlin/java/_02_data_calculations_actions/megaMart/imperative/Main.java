package java._02_data_calculations_actions.megaMart.imperative;

import java._02_data_calculations_actions.megaMart.givenCode.CartItem;
import java._02_data_calculations_actions.megaMart.givenCode.DOM;

import static java._02_data_calculations_actions.megaMart.givenCode.App.shoppingCart;
import static java._02_data_calculations_actions.megaMart.givenCode.App.shoppingCartTotal;

public class Main {

    public static void addItemToCart(String name, Double price) {
        shoppingCart.add(new CartItem(name, price));    // Reading a global variable is an action
                                                        // Updating a non-local list is an action
        calcCartTotal();
    }

    public static void calcCartTotal() {
        shoppingCartTotal = 0.0;
        for (CartItem item : shoppingCart) {
            shoppingCartTotal += item.price();  // Updating a global variable is an action
        }
        DOM.setCartTotal(shoppingCartTotal);    // Updating the DOM is an action
                                                // Reading a global variable is an action
        updateShippingIcons();
        updateTaxDom();
    }

    public static void updateShippingIcons() {
        var buyButtons = DOM.getBuyButtons();   // Reading the DOM is an action

        for (var button : buyButtons) {
            // There is a business rule embedded here (carts >= 20 get free shipping)
            // This code also requires the shoppingCartTotal to be initialized before testing
            if (button.item().price() + shoppingCartTotal > 20.0) {
                // Can't test this code without a mock DOM
                button.showFreeShippingIcon();      // Updating the DOM is an action
            } else {
                button.hideFreeShippingIcon();      // Updating the DOM is an action
            }
        }
    }

    public static void updateTaxDom() {
        var tax = shoppingCartTotal * 0.13;  // Reading a global variable is an action
        DOM.setTax(tax);                     // Updating the DOM is an action
    }
}

