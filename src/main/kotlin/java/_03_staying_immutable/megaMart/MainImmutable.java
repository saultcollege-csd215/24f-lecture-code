package java._03_staying_immutable.megaMart;
import java._03_staying_immutable.megaMart.givenCode.*;

import java._03_staying_immutable.megaMart.givenCode.*;
import java.util.ArrayList;
import java.util.List;

import static java._03_staying_immutable.megaMart.givenCode.App.shoppingCart;

/*********************************
 * This class is a re-implementation of MainMutable using the copy-on-write and defensive copy techniques.
 */
public class MainImmutable {

    public static void addItemToCart(String name, Double price) {
        shoppingCart = addItem(shoppingCart, new CartItem(name, price));    // Updating/reading a global variable is an action
        // calcCartTotal();                                                 // The lines below fit better in this function than as a separate function
        var total = calcTotal(shoppingCart);
        DOM.setCartTotal(total);                    // Updating the DOM is an action
        updateShippingIcons(shoppingCart);          // This is still an action, but it now uses an explicit input
        updateTaxDom(total);

        // REPLACE THIS...
//        /*** UNSAFE ***/
//        // We have no control over this function, and it might change shoppingCart
//        // We need to do some defensive copying!
//        App.blackFridayPromotion(shoppingCart);
        // ...WITH THIS:
        shoppingCart = safeBlackFridayPromotion(shoppingCart);
    }

    public static List<CartItem> safeBlackFridayPromotion(List<CartItem> cart) {
        // We need to do some defensive copying!
        var copy = deepCopyCart(cart);   // Create a defensive copy to pass to the unsafe function below
        App.blackFridayPromotion(copy);  // This call may change the argument we pass, so we pass a defensive copy
        return deepCopyCart(copy);       // To prevent the unsafe code from having a reference to our input copy and being able to alter it
                                         // we do another defensive copy here and return that.
    }

    public static List<PayrollCheque> safePayrollCalc(List<Employee> employees) {
        // Since payrollCalc is UNSAFE (it may change the given employees list and it may yield a reference to a mutable data structure)
        // we need to do some defensive copying!
        var copy = deepCopyEmployees(employees);   // Create a defensive copy to pass to the unsafe function below
        var result = App.payrollCalc(copy);
        // The result may be a reference to a mutable data structure that other unsafe code also has references to
        // so we need to do some defensive copying of the result here too!
        return deepCopyPayrollCheques(result);
    }

    public static List<CartItem> addItem(List<CartItem> items, CartItem item) {
        var newItems = new ArrayList<>(items);
        newItems.add(item);
        return newItems;
    }

    /*** REFACTORED ***/
    // This function is an action because it updates the given list in-place (an IMPLICIT OUTPUT)
    public static List<CartItem> removeItemByName(List<CartItem> cart, String name) {
        // REPLACE THIS...
//        for ( var item : cart ) {
//            if ( item.name() == name ) {
//                cart.remove(item);            // updating a list in-place is an IMPLICIT OUTPUT!
//                return;
//            }
//        }
        // ...WITH THIS:
        // Use copy-on-write to make a copy of the list before we update it
        var copy = new ArrayList<CartItem>();
        for ( var item : cart ) {
            if (!item.name().equals(name)) {  // Copy all items except the one we want to remove
                copy.add(item);
            }
        }
        return copy;
    }

    public static void deleteHandler(String name) {
        // REPLACE THIS...
//        /*** UNSAFE ***/
//        // removeItemByName updates the shoppingCart list in-place (an IMPLICIT OUTPUT)
//        removeItemByName(shoppingCart, name);
        // ...WITH THIS:
        // We have refactored removeItemByName using copy-on-write
        // So need to update the shoppingCart variable to point to the new list returned by removeItemByName
        shoppingCart = removeItemByName(shoppingCart, name);



        var total = calcTotal(shoppingCart);   // This is fine because calcTotal is in the SAFE ZONE (it's a calculation)
        DOM.setCartTotal(total);
        updateShippingIcons(shoppingCart);     // This is fine because we made shoppingCart into an EXPLICIT INPUT last unit
        // updateShippingIcons will not change shoppingCart; it is in the SAFE ZONE
        updateTaxDom(total);
    }

    public static void updateShippingIcons(List<CartItem> cart) {

        // REPLACE THIS...
//        /*** UNSAFE ***/
//        // We are getting mutable data from an untrusted source!
//        // The data referred to by buyButtons may change at any time!
//        // We need to do some defensive copying!
//        var buyButtons = DOM.getBuyButtons();
        // ...WITH THIS:
        var buyButtons = deepCopyBuyButtons(DOM.getBuyButtons());

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

    /**************************************************
     * Some utility function for deep copying various data structures
     */

    public static List<CartItem> deepCopyCart(List<CartItem> cart) {
        var copy = new ArrayList<CartItem>();
        for ( var item : cart ) {
            copy.add(new CartItem(item.name(), item.price()));
        }
        return copy;
    }

    public static List<Employee> deepCopyEmployees(List<Employee> employees) {
        var copy = new ArrayList<Employee>();
        for ( var item : employees ) {
            copy.add(new Employee(item.name(), item.email()));
        }
        return copy;
    }

    public static List<PayrollCheque> deepCopyPayrollCheques(List<PayrollCheque> cheques) {
        var copy = new ArrayList<PayrollCheque>();
        for ( var item : cheques ) {
            // NOTE that a deep copy also requires a deep copy of the employee object
            copy.add(new PayrollCheque(new Employee(item.employee().name(), item.employee().email()), item.amount()));
        }
        return copy;
    }

    public static List<BuyButton> deepCopyBuyButtons(List<BuyButton> buttons) {
        var copy = new ArrayList<>(buttons);
        for ( var button : buttons ) {
            copy.add(new BuyButton(button.item()));
        }
        return copy;
    }

}
