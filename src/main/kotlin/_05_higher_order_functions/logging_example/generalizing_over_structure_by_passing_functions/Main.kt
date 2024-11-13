package _05_higher_order_functions.logging_example.generalizing_over_structure_by_passing_functions

fun anotherFunctionThatNeedsLogging(a: String, b: Int) {
    println("This is another function that needs logging: $a, $b")
}

fun aFunctionThatNeedsLogging() {
    println("This is a function that needs logging")
}

fun withLogging(f: () -> Unit) {
    try {
        f()
    } catch (e: Exception) {
        error("An exception occurred: ${e.message}")
    }

}

fun main() {

    // Instead of finding the possibly many places where we need to log exceptions, we can just wrap the function in a logging function.
    try {
        aFunctionThatNeedsLogging()
    } catch (e: Exception) {
        error("An exception occurred: ${e.message}")
    }

    // This does the same thing as above, but is more concise and easier to read.
    withLogging { aFunctionThatNeedsLogging() }
    // OR
    withLogging(::aFunctionThatNeedsLogging)

    // This doesn't work (type mismatch):
    // withLogging(::anotherFunctionThatNeedsLogging)
    // But this is fine:
    withLogging { anotherFunctionThatNeedsLogging("hello", 42) }
}