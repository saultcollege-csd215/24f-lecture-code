package java._04_stratified_design.megamart.stratified.dom;

import java._04_stratified_design.megamart.stratified.CartItem;

import java.util.ArrayList;
import java.util.List;

// LAYER: DOM operations
public record BuyButton(CartItem item) {

    public void showFreeShippingIcon() {
        System.out.println("Free shipping icon");
    }

    public void hideFreeShippingIcon() {
        System.out.println("No free shipping icon");
    }

    public static List<BuyButton> deepCopy(List<BuyButton> buttons) {
        var copy = new ArrayList<>(buttons);
        for ( var button : buttons ) {
            copy.add(new BuyButton(button.item()));
        }
        return copy;
    }
}
