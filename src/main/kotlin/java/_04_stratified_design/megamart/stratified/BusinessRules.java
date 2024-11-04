package java._04_stratified_design.megamart.stratified;

// NOTE: This class contains only general business rules, not specific to any particular domain.
// It is a quite "low-level" layer that many other layers can depend on.
// BUT it does not itself depend on any other layers
public class BusinessRules {

    public static double calcTax(double amount) {
        return amount * 0.13;
    }

}
