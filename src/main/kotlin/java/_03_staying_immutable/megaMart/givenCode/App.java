package java._03_staying_immutable.megaMart.givenCode;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static List<CartItem> shoppingCart = new ArrayList<>();
    public static double shoppingCartTotal = 0.0;


    /****************************************************
     * For the sake of example, we will pretend that these methods are UNSAFE,
     * that is, they may treat input as mutable and may return a reference to a mutable data structure.
     */
    public static void blackFridayPromotion(List<CartItem> cart) {
        for ( var item : cart ) {
            System.out.println("Black Friday Promotion: " + item);
        }
    }

    // Here, we set a global mutable variable that is used by other code in unsafe ways
    public static List<PayrollCheque> currentPayrollChecks = List.of(
            new PayrollCheque(new Employee("Ali Array", "a@email.com"), 1200.0),
            new PayrollCheque(new Employee("Bob Builder", "b@email.com"), 1500),
            new PayrollCheque(new Employee("Charlie Collection", "c@email.com"), 1800)
    );

    public static List<PayrollCheque> payrollCalc(List<Employee> employees) {

        // Again for the sake of example, assume that code might update the given employees list,
        // for example it might remove all employees currently on vacation

        // And it might return a data structure that is mutable and shared with other code,
        // for example by returning a reference to a global variable
        return currentPayrollChecks;
    }
}
