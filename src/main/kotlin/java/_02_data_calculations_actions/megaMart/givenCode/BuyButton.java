package java._02_data_calculations_actions.megaMart.givenCode;

public record BuyButton(CartItem item) {

    public void showFreeShippingIcon() {
        System.out.println("Free shipping icon");
    }

    public void hideFreeShippingIcon() {
        System.out.println("No free shipping icon");
    }
}
