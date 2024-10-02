package _03_staying_immutable.extensionMethods

/**
 * An extension method on Strings that randomly shuffles the characters
 */
// Note the syntax here: the name of the class being extended (String)
// is followed by a dot and the name of the extension method (shuffled)
fun String.shuffled() =
    // 'this' works in here just as it would in a regular member function
    // it is a reference to the object on which the method was called.
    this.toList().shuffled().joinToString("")

/**
 *  An extension method on Lists that returns
 *  a new list with two of each item in the original list
 */
// NOTE: This is a generic function over any type T
// (i.e. it can be called on any List<T> where T is any type)
fun <T> List<T>.doubleUp(): List<T> {
    val result = mutableListOf<T>()
    for (item in this) {
        result.add(item)
        result.add(item)
    }
    return result
}

fun main() {

    val s1 = "ABCDEFG"
    println(s1.shuffled())

    val L1 = listOf(1, 2, 3)
    val L2 = L1.doubleUp()
    val L3 = listOf("One", "Two", "Three")
    val L4 = L3.doubleUp()
    println("L1: $L1")
    println("L2: $L2")
    println("L3: $L3")
    println("L4: $L4")
}