package _02_data_calculations_actions.referentialTransparency

fun hypotenuse(a: Double, b: Double) = Math.sqrt(square(a) + square(b))
fun square(n: Double) = n * n

fun main() {
    println(square(hypotenuse(square(3.0), square(4.0))))
}