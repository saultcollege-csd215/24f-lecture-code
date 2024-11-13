package _05_higher_order_functions.scope_functions

data class Person(val name: String, var age: Int, var city: String) {
    fun incrementAge() {
        age = age + 1
    }
    fun moveTo(newCity: String) {
        city = newCity
    }
}

fun main() {

    val person = Person("Alice", 20, "Amsterdam")

    // Without let:
    println(person)
    person.moveTo("London")
    person.incrementAge()
    println(person)

    // With let:
    person.let {
        println(it)
        it.moveTo("London")
        it.incrementAge()
        println(it)
    }

}