package _01_programming_paradigms.pureFunctions

var aGlobalVariable = 100

class Person(val name: String, var age: Int)

val L = listOf(1, 2, 3, 4, 5, 6)

/*********************
 * PURE FUNCTIONS
 *
 * Pure functions are functions that always return the same output for the same input.
 * They don't have side effects, like changing a global variable or printing to the console.
 */

fun hello() = "Hello, World!"
fun repeat(s: String, n: Int) = s.repeat(n)
fun sum(a: Int, b: Int) = a + b
fun isEven(n: Int) = n % 2 == 0
fun oneYearOlder(person: Person) = Person(person.name, person.age + 1)
fun addTo(list: List<Int>, n: Int) = list + n

/*********************
 * IMPURE FUNCTIONS
 */

fun printHello() = println("Hello, World!")
fun getInput() = readlnOrNull()
fun changeAGlobal() {
    // This is not a pure function because it changes a global variable.
    // It's better to avoid changing global variables in your code.
    aGlobalVariable += 1
}

fun incrementAge(person: Person) {
    // This is not a pure function because it changes the age of the person.
    // It's better to avoid changing the properties of objects in your code.
    // (See oneYearOlder for a better way to do this.)
    person.age += 1
}

fun appendItem(list: MutableList<Int>, n: Int) {
    // This is not a pure function because it changes the list.
    // It's better to avoid changing the properties of objects in your code.
    // (See addTo for a better way to do this.)
    list.add(n)
}