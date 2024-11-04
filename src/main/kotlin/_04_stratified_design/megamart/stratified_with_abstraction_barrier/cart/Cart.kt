package _04_stratified_design.megamart.stratified_with_abstraction_barrier.cart

// See note on the tax function below about why we're importing calcTax here rather than directly in the Dom layer
import _04_stratified_design.megamart.stratified_with_abstraction_barrier.businessRules.calcTax

data class CartItem(val name: String, val price: Double)

// We can make this layer a better abstraction barrier by using a typealias to hide the implementation details.
// No layer above this one needs to know that a Cart is just a List of CartItems.
// In fact, we should be able to change the implementation of Cart to something else entirely
// without affecting any layers that depend on this layer.
// For example, we could change it to a Map<String, CartItem> if we wanted to!
//
// NOTE: We also use extension functions below to make working with Cart objects more like working with a class.
// (Compare this with the 'pass the object you're manipulating as a parameter' approach in previous code.)
// Neither of these approaches is necessarily better than the other in all cases, but the extension function approach
// can be more concise and easier to read in some cases.

// In order to make this layer an abstraction barrier, we are providing the calculation of tax here.
// This way, the layers above don't need to know about the lower-level calcTax function.
fun Cart.tax() = calcTax(this.calcTotal())

/******************************************
 * List implementation
 */

//typealias Cart = List<CartItem>
//
//fun Cart.addItem(item: CartItem) =
//    this + item
//
//fun Cart.removeItemByName(name: String): Cart {
//    val item = this.getItemByName(name) ?: return this
//
//    return this - item
//}
//
//fun Cart.setPriceByName(name: String, price: Double): Cart {
//
//    val item = getItemByName(name) ?: return this
//
//    val updatedItem = item.copy(price = price)
//
//    return this.map { if (it.name == name) updatedItem else it }
//
//}
//
//fun Cart.deepCopy() = this.map { it.copy() }
//
//fun Cart.calcTotal() = this.sumOf { it.price }
//
//fun Cart.isNameInCart(name: String): Boolean {
//
//    // At first, we might write this function like this:
////    for ( item in this ) {
////        if ( item.name == name ) {
////            return true;
////        }
////    }
////    return false
//
//    // But after writing getItemByName, we can recognize that lookup-by-name pattern again
//    // and reuse it to perform the necessary calculation here!
//    return this.getItemByName(name) != null
//
//    // FYI, using Kotlin's 'any' would also be quite simple here.
//    // any returns true if any of the items in the list are true for the given condition.
//    // return this.any { it.name == name }
//}
//
//
//fun Cart.getItemByName(name: String) = this.find { it.name == name }
//


/*******************************
 * Map implementation
 */

typealias Cart = Map<String, CartItem>

fun Cart.addItem(item: CartItem) =
    this + (item.name to item)

fun Cart.removeItemByName(name: String) =
    this - name

fun Cart.setPriceByName(name: String, price: Double): Cart {
    // Note that in the Map implementation, the getItemByName function is not necessary
    // because we are using item names as the Map keys, so getItemByName is simply a
    // normal map lookup using []
    val item = this[name] ?: return this

    val updatedItem = item.copy(price = price)

    return this + (name to updatedItem)

}

fun Cart.deepCopy() = this.mapValues { it.value.copy() }

fun Cart.calcTotal() = this.values.sumOf { it.price }

fun Cart.isNameInCart(name: String) = this.containsKey(name)