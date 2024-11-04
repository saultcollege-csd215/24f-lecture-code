package java._04_stratified_design.megamart.stratified_with_abstractionbarrier;

import java._04_stratified_design.megamart.stratified_with_abstractionbarrier.dom.DOM;

public class App {

    public static void main(String[] args) {
        var c = new Cart();

        c = c.addItem(new CartItem("apple", 0.99));
        c = c.addItem(new CartItem("banana", 1.29));

        DOM.displayCartTotal(c);

    }
}
