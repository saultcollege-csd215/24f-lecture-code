package _06_other_fp_concepts.product_vs_sum_types.shapes.using_sum_types

import kotlin.math.abs
import kotlin.math.hypot

fun main() {
    val shapes = listOf(
        Shape.Circle(Point(0.0, 0.0), 1.0),
        Shape.Rectangle(Point(0.0, 0.0), 2.0, 3.0),
        Shape.Polygon(
            listOf(
                Point(0.0, 0.0),
                Point(1.0, 0.0),
                Point(1.0, 1.0),
                Point(0.0, 1.0)
            )
        )
    )

    // Polymorphism is achieved via pattern matching in the various functions.
    // The sealed interface defines the set of possible types.
    // The switch expressions within each function define the specific implementation of the behaviour
    // for each of the data types defined in the sealed interface.
    // At compile time the compiler will check that all the cases are covered, reducing the risk of runtime errors.
    shapes.forEach {
        println("Area: ${area(it)}")
        println("Perimeter: ${perimeter(it)}")
        println()
    }
}

// Point is a product type
// (The number of possible values is the PRODUCT of the number of possible values of its fields)
data class Point(val x: Double, val y: Double)

// The sealed interface defines the set of possible subtypes
// "A Shape must be one of Circle, Rectangle, Polygon"
// This is a sum type
// (The number of possible values is the SUM of the number of possible values of its subtypes)
sealed interface Shape {

    // Each individual subtype here is itself a product type
    data class Circle(val center: Point, val radius: Double) : Shape
    data class Rectangle(val topLeft: Point, val width: Double, val height: Double) : Shape
    data class Polygon(val vertices: List<Point>) : Shape
}

// In this approach, we define functions that operate on Shape objects
// (As opposed to the approach in using_product_types, 
// where the functions are defined as class methods on subtypes of Shape)
// We use pattern matching to determine the specific subtype of Shape
// and then perform the appropriate calculations based on that subtype.
// This is a more functional programming style of working with data.
fun area(shape: Shape): Double = when (shape) {
    // With pattern matching, the compiler will warn us
    // if we forget to handle a specific subtype
    is Shape.Circle -> Math.PI * shape.radius * shape.radius
    is Shape.Rectangle -> shape.width * shape.height
    is Shape.Polygon -> {
        // Calculate the area of a polygon using the shoelace formula
        // https://en.wikipedia.org/wiki/Shoelace_formula
        var area = 0.0
        for (i in shape.vertices.indices) {
            val p1 = shape.vertices[i]
            val p2 = shape.vertices[(i + 1) % shape.vertices.size]
            area += p1.x * p2.y - p2.x * p1.y
        }
        abs(area) / 2
        // Or, using a more 'functional' approach:
//        abs(shape.vertices.zipWithNext { p1, p2 ->
//            p1.x * p2.y - p2.x * p1.y
//        }.sum() / 2)
    }

}

fun perimeter(shape: Shape): Double = when (shape) {
    is Shape.Circle -> 2 * Math.PI * shape.radius
    is Shape.Rectangle -> 2 * (shape.width + shape.height)
    is Shape.Polygon -> {
        var perimeter = 0.0
        for (i in shape.vertices.indices) {
            val p1 = shape.vertices[i]
            val p2 = shape.vertices[(i + 1) % shape.vertices.size]
            perimeter += hypot(p1.x - p2.x, p1.y - p2.y)
        }
        perimeter
        // OR, using a more functional approach:
//        shape.vertices.zipWithNext { p1, p2 ->
//            hypot(p1.x - p2.x, p1.y - p2.y)
//        }.sum()
    }
}