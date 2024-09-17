package _01_programming_paradigms.lambdaExpressions

val L = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

// Kotlin has a special syntax for lambda expressions (aka "anonymous", or "inline" functions).

// Here's a lambda expression that takes an Int and returns an Int:
val square = { n: Int -> n * n }

// Here's a lambda expression that takes two Ints and returns an Int:
val add = { a: Int, b: Int -> a + b }

fun main() {
    // Kotlin's collection classes have typical functional programming methods like map, filter, and reduce
    // which take lambda expressions as arguments.

    val squares = L.map(square)
    // This is the equivalent of...
    val squares2 = L.map({ n -> n * n })
    // ...but the lambda expression can be moved outside the parentheses if it's the last argument.
    val squares3 = L.map { n -> n * n }
    // ...and if there is just one parameter, there is an implicit variable named "it" inside the lambda body.
    val squares4 = L.map { it * it }

    // Here's an example using filter:
    val evens = L.filter { it % 2 == 0 }
}

