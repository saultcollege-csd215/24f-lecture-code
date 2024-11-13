package _05_higher_order_functions.closures

fun hof(f: (String) -> Unit) {
    f("Hello, world!")
}

fun main() {
    val x = 1

    hof { println("$x : $it") }
}