package _02_data_calculations_actions.dataClasses

import java.time.LocalDate  // Using JDK classes in Kotlin works!

data class Address(val street: String, val city: String, val postalCode: String)
data class Person(
    val name: String,
    val birthday: LocalDate,
    val height: Int,
    val eyeColor: EyeColor,
    val address: Address
)

enum class EyeColor {
    BROWN, BLUE, GREEN, HAZEL, GRAY
}


fun main() {

    val p1 = Person(
        "Alice",
        LocalDate.of(1990, 1, 1),
        170,
        EyeColor.BROWN,
        Address("123 Main St", "Toronto", "M1M 1M1")
    )
    val p2 = Person(
        "Bob",
        LocalDate.of(1991, 2, 2),
        180,
        EyeColor.BLUE,
        Address("456 Queen St", "Toronto", "M2M 2M2")
    )

    val p3 = p1.copy(name = "Alice's Twin Sister")

    println(p1)
    println(p2)
    println(p3)
}