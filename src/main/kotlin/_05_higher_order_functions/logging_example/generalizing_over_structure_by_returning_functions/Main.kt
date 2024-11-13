package _05_higher_order_functions.logging_example.generalizing_over_structure_by_returning_functions

import _05_higher_order_functions.logging_example.generalizing_over_structure_by_passing_functions.withLogging

// Using this function, we can give any function that takes no arguments and returns nothing the ability to log exceptions.
fun makeLoggingFunction(f: () -> Unit): () -> Unit {
    // This is the structure we are generalizing over, but we note that the structure is
    // the same as the 'by passing functions' example so...
//    return {
//        try {
//            f()
//        } catch (e: Exception) {
//            error("An exception occurred: ${e.message}")
//        }
//    }
    // ...we can just call the 'by passing functions' example...
    return { withLogging(f) }   // Note, though that we are returning a FUNCTION (lambda) here
                                // withLogging will not be called until the returned function is called
}

// Here's the same idea for functions that take two arguments.
fun makeLoggingFunction2(f: (String, Int) -> Unit): (String, Int) -> Unit {
    return { a, b -> withLogging { f(a, b) } }
}


// The only changes required:
// 1: Change the name of the original function... (here, we add two underscores)
fun __anotherFunctionThatNeedsLogging(a: String, b: Int) {
    println("This is another function that needs logging: $a, $b")
}

fun __aFunctionThatNeedsLogging() {
    println("This is a function that needs logging")
}



fun main() {

    // 2: ...and pass the new name to the makeLoggingFunction function to get a new function that logs exceptions.
    val aFunctionThatNeedsLogging = makeLoggingFunction(::__aFunctionThatNeedsLogging)
    val anotherFunctionThatNeedsLogging = makeLoggingFunction2(::__anotherFunctionThatNeedsLogging)


    // Now, all call points that exist in the codebase have the ability to log exceptions.
    // AND we don't have to change the call points at all to do so!
    // If we had thousands of calls to these functions,
    // the above 2 steps are much easier than finding and changing all the calls
    aFunctionThatNeedsLogging()

    anotherFunctionThatNeedsLogging("hello", 42)

}