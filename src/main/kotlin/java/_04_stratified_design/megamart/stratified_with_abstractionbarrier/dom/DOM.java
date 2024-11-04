package java._04_stratified_design.megamart.stratified_with_abstractionbarrier.dom;

import java._04_stratified_design.megamart.stratified_with_abstractionbarrier.BusinessRules;
import java._04_stratified_design.megamart.stratified_with_abstractionbarrier.Cart;
import java._04_stratified_design.megamart.stratified_with_abstractionbarrier.CartItem;
import java._04_stratified_design.megamart.stratified_with_abstractionbarrier.CartRules;

import java.util.List;

public class DOM {

    public static void addItemToCart(Cart cart, String name, Double price) {
        cart = cart.addItem(new CartItem(name, price));    // Relegate addition of cart items to Cart layer
        var total = cart.calcTotal();                        // Cart layer handles total calculation
        setCartTotal(total);                                     // Setting the total in the DOM is part of this layer
        updateShippingIcons(cart);                               // ...as is updating the shipping icons
        updateTaxDom(total);                                     // ...as is updating the tax in the DOM
    }

    public static List<BuyButton> getBuyButtons() {
        return List.of(
                new BuyButton(new CartItem("Shoes", 19.99)),
                new BuyButton(new CartItem("Shirt", 9.99)),
                new BuyButton(new CartItem("Pants", 14.99)),
                new BuyButton(new CartItem("Socks", 4.99))
        );
    }

    public static void setCartTotal(Double total) {
        System.out.println("Cart total: " + total);
    }

    public static void setTax(Double tax) {
        System.out.println("Tax: " + tax);
    }

    // LAYER: DOM operations
    public static void updateShippingIcons(Cart cart) {

        var buyButtons = BuyButton.deepCopy(getBuyButtons());  // The deep copying code is part of the BuyButton class now, a better 'fit'

        for (var button : buyButtons) {
            // The 'getsFreeShipping' function is now part of the CartRules layer
            // The 'addItem' function is in the lower-level Cart layer
            if (CartRules.getsFreeShipping(cart.addItem(button.item()))) {
                button.showFreeShippingIcon();
            } else {
                button.hideFreeShippingIcon();
            }
        }
    }

    public static void updateTaxDom(double shoppingCartTotal) {
        // Calculation of a tax amount is now part of the BusinessRules layer
        setTax(BusinessRules.calcTax(shoppingCartTotal));
    }

    public static void displayCartTotal(Cart cart) {
        System.out.println(cart.calcTotal());
    }

}
