package _06_other_fp_concepts.product_vs_sum_types.shapes.using_product_types

import kotlin.math.abs
import kotlin.math.hypot

fun main() {
    val shapes = listOf(
        Circle(Point(0.0, 0.0), 1.0),
        Rectangle(Point(0.0, 0.0), 2.0, 3.0),
        Polygon(
            listOf(
                Point(0.0, 0.0),
                Point(1.0, 0.0),
                Point(1.0, 1.0),
                Point(0.0, 1.0)
            )
        )
    )

    // Polymorphism is achieved by implementing the Shape interface.
    // The interface defines the common methods that all shapes must have.
    // The classes defined the specific implementations of those methods for each shape.
    // At run-time, the JVM will call the appropriate method based on the actual type of the object.
    shapes.forEach {
        println("Area: ${it.area()}")
        println("Perimeter: ${it.perimeter()}")
        println("Vertices: ${it.vertexCount()}")
        println("Edges: ${it.edgeCount()}")
        println()
    }
}

data class Point(val x: Double, val y: Double)

interface Shape {
    fun area(): Double
    fun perimeter(): Double
    fun vertexCount(): Int
    fun edgeCount(): Int
}

class Circle(val center: Point, val radius: Double) : Shape {
    override fun area(): Double = Math.PI * radius * radius
    override fun perimeter(): Double = 2 * Math.PI * radius
    override fun vertexCount(): Int = 0
    override fun edgeCount(): Int = 1
}

class Rectangle(val topLeft: Point, val width: Double, val height: Double) : Shape {
    override fun area(): Double = width * height
    override fun perimeter(): Double = 2 * (width + height)
    override fun vertexCount(): Int = 4
    override fun edgeCount(): Int = 4
}

/**
 * Assumes vertices are given in counter-clockwise order
 */
class Polygon(val vertices: List<Point>) : Shape {

    // Returns the next vertex index after the given one
    // (wrapping around to the first vertex if the given index is the last one)
    private fun nextVertex(i: Int) = (i + 1) % vertices.size

    override fun area(): Double {
        var area = 0.0
        for (i in vertices.indices) {
            val j = nextVertex(i)
            area += vertices[i].x * vertices[j].y
            area -= vertices[j].x * vertices[i].y
        }
        return abs(area) / 2
    }

    override fun perimeter(): Double {
        var perimeter = 0.0
        for (i in vertices.indices) {
            val j = nextVertex(i)
            perimeter += hypot(vertices[i].x - vertices[j].x, vertices[i].y - vertices[j].y)
        }
        return perimeter
    }

    override fun vertexCount(): Int = vertices.size
    override fun edgeCount(): Int = vertices.size
}
