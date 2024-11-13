package _05_higher_order_functions

// These functions have the "implicit argument in function name" code smell...
fun multiplyByTwo(x: Double) = x * 2
fun multiplyByTwelve(x: Double) = x * 12
fun multiplyByPi(x: Double) = x * Math.PI
fun multiplyByE(x: Double) = x * Math.E

// Here's a more general function that can be used to create the above functions
fun multiplyBy(factor: Double, x: Double) = factor * x


fun forEach(list: List<Int>, action: (Int) -> Unit) {
    for (i in list) {
        action(i)
    }
}

fun main() {
    val L = listOf(1, 2, 3, 4, 56)


    L.map { it * 2 }.filter { it % 2 == 0 }.forEach { println(it) }

    forEach(L, ::println)
}