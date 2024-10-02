package _03_staying_immutable.copyOnWrite

import java.time.LocalDate

enum class EyeColor { BROWN, BLUE, GRAY, HAZEL, GREEN }
data class Person(val name: String, val birthday: LocalDate, val eyeColor: EyeColor)

fun main() {

    val p1 = Person("Alice", LocalDate.of(1990, 1, 1), EyeColor.GREEN)

    // Kotlin data classes have a copy method
    val p1_copy = p1.copy()        // New object with all the same property values
    val p2 = p1.copy(name="Bob")   // New object with a different name
    val p3 = p1.copy(name="Charlie", eyeColor = EyeColor.BROWN)  // New object with a different name and eye color


    // Kotlin collections have a copyOnWrite add operator (+)
    var numbers = listOf(1, 2, 3, 4, 5)
    numbers = numbers + 6                  // Add the number 6 to the list
    numbers = numbers + listOf(7, 8, 9)    // Add the numbers 7, 8, and 9 to the list
    numbers = numbers - 3                  // Remove 3 from the list

    // Works on Maps too
    var tally = mapOf("apples" to 3, "bananas" to 1, "cherries" to 6)
    tally = tally + ("durian" to 1)
    tally = tally + mapOf("elderberries" to 138, "grapes" to 74)

    // Many of Kotlin's built-in collection methods also do copy-on-write rather than in-place changes
    numbers = numbers.sorted()     // (Makes a new sorted list, unlike numbers.sort(), which is an in-place change)
    numbers = numbers.reversed()   // (Makes a new reversed list, unlike numbers.reverse(), which reverses in-place)

    // The operations below only work on mutable lists because they make in-place changes
    var mutableNumbers = mutableListOf(1, 3,5, 7, 43, 3, 6)
    mutableNumbers.sort()          // Note that we do not need to re-assign the variable because of the in-place change
    mutableNumbers.reverse()

}