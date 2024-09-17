package java._02_data_calculations_actions.megaMart.givenCode;

import java.util.List;

/**
 * This class represents the DOM (Document Object Model) of the megaMart website.
 * It is a mockup of some of the operations that would be performed on the website.
 */
public class DOM {

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

}
