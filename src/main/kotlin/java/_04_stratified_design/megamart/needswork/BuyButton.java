package java._04_stratified_design.megamart.needswork;

// LAYER: DOM operations
public record BuyButton(CartItem item) {

    public void showFreeShippingIcon() {
        System.out.println("Free shipping icon");
    }

    public void hideFreeShippingIcon() {
        System.out.println("No free shipping icon");
    }

}
