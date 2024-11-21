package java._06_other_fp_concepts.unit_conversion;

import java.util.function.Function;

public class App {
    public static double convert(double offset, double factor, double amount) {
        return (amount + offset) * factor;
    }

    // These are specific implementations of the general convert function above
//    public static double miToKm(double from) { return convert(0, 1.609344, from); }
//    public static double kmToMi(double from) { return convert(0, 0.621371, from); }
//    public static double lbToKg(double from) { return convert(0, 0.45359237, from); }
//    public static double kgToLb(double from) { return convert(0, 2.20462262185, from); }
//    public static double cToF(double from) { return convert(32, 1.8, from); }
//    public static double fToC(double from) { return convert(-32, 0.55555555556, from); }

    // This function provides the ability to create a specific converter function by specifying
    // ONLY the factor and offset, and leaving the amount to be converted unspecified
    public static Function<Double, Double> makeConverter(double offset, double factor) {
        return amount -> convert(offset, factor, amount);
    }

    // Here is another way to build the same specific converters as above, but using the makeConverter function
//    public static double miToKm(double amount) { return makeConverter(1.609344, 0).apply(amount); }
//    public static double kmToMi(double amount) { return makeConverter(0.621371, 0).apply(amount); }
//    public static double lbToKg(double amount) { return makeConverter(0.45359237, 0).apply(amount); }
//    public static double kgToLb(double amount) { return makeConverter(2.20462262185, 0).apply(amount); }
//    public static double cToF(double amount) { return makeConverter(1.8, 32).apply(amount); }
//    public static double fToC(double amount) { return makeConverter(0.55555555556, -32).apply(amount); }


    // In languages that feature currying (e.g. Haskell, Scala, F#), we could write our specific converters like this:
    // var miToKm = convert(0, 1.609344);        // NOTE that we call the ORIGINAL general convert function
    // var kmToMi = convert(0, 0.621371);        // with only the offset and factor specified
    // var lbToKg = convert(0, 0.45359237);
    // var kgToLb = convert(0, 2.20462262185);
    // var cToF = convert(32, 1.8);
    // var fToC = convert(-32, 0.55555555556);

    // In Java, we can't do this because Java doesn't support currying, but if we could, we would be able to write
    // miToKm(10) to provide that final argument (the amount to be converted), and get back the converted value

    // In other words, in languages that feature currying, we get builder/factory functions for free by simply
    // leaving off some number of arguments when calling a function that takes multiple arguments.
    // The value returned is a function that takes the remaining arguments and returns the result.


    // In Scala, we could write our specific converters like this:
    // val miToKm = convert(0, 1.609344, _: Double)        // NOTE that we call the ORIGINAL general convert function

    // In F# or Haskell, we could write our specific converters like this:
    // let miToKm = convert 0.0 1.609344        // NOTE that we call the ORIGINAL general convert function

    // In Racket or Clojure, we could write our specific converters like this:
    // (define miToKm (convert 0.0 1.609344))        ; NOTE that we call the ORIGINAL general convert function


}
