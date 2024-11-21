package _06_other_fp_concepts.currying

fun convert(offset: Double, factor: Double, amount: Double)
    = (amount + offset) * factor

// Below are specific implementations of the general convert function above
//fun miToKm(mi: Double) = convert(0.0, 1.60934, mi)
//fun kmToMi(km: Double) = convert(0.0, 0.621371, km)
//fun lbToKg(lb: Double) = convert(0.0, 0.453592, lb)
//fun kgToLb(kg: Double) = convert(0.0, 2.20462, kg)
//fun fToC(f: Double) = convert(-32.0, 5.0 / 9.0, f)
//fun cToF(c: Double) = convert(32.0, 9.0 / 5.0, c)

// This function provides the ability to create a specific converter
// by providing ONLY the offset and factor, and leaving the amount to be provided later
fun makeConverter(offset: Double, factor: Double)
    // NOTE that the return type is a function that takes a Double (the amount to convert) and returns a Double
    = { amount: Double -> convert(offset, factor, amount) }

// Now we can create specific converters by providing only the offset and factor
// This is the same set of converters as above, but created using our makeConverter function
val miToKm = makeConverter(0.0, 1.60934)
val kmToMi = makeConverter(0.0, 0.621371)
val lbToKg = makeConverter(0.0, 0.453592)
val kgToLb = makeConverter(0.0, 2.20462)
val fToC = makeConverter(-32.0, 5.0 / 9.0)
val cToF = makeConverter(32.0, 9.0 / 5.0)

// In languages that feature currying (e.g. Haskell, Scala, F#)
// we would not need this intermediary makeConverter function,
// and could instead create the specific converters directly from the general convert function:
//val miToKm = convert(0.0, 1.60934)
//val kmToMi = convert(0.0, 0.621371)
//val lbToKg = convert(0.0, 0.453592)
//val kgToLb = convert(0.0, 2.20462)
//val fToC = convert(-32.0, 5.0 / 9.0)
//val cToF = convert(32.0, 9.0 / 5.0)

// Scala example:
// val miToKm = convert(0.0, 1.60934) _

// F# or Haskell example:
// let miToKm = convert 0.0 1.60934


fun main() {
    println(miToKm(1.0))    // 1.60934
    println(kmToMi(1.0))    // 0.621371
    println(lbToKg(1.0))    // 0.453592
    println(kgToLb(1.0))    // 2.20462
    println(fToC(32.0))     // 0.0
    println(cToF(0.0))      // 32.0
}