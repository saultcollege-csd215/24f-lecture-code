package _04_stratified_design.megamart.stratified.marketing

import _04_stratified_design.megamart.stratified.cart.*

fun getsFreeShipping(cart: List<CartItem>) = calcTotal(cart) > 20.0

// NOTE: Compare this method with the addFreeTieClip in the 'needswork' package.
// For which one is it easier to understand the purpose of the code in the function body?  Which one is simpler?
fun addFreeTieClip(cart: List<CartItem>): List<CartItem> {

    // Using our new isNameInCart method to simplify finding the items
    val hasTie = isNameInCart(cart, "tie")
    val hasTieClip = isNameInCart(cart, "tie clip")

    if ( hasTie && !hasTieClip ) {
        return addItem(cart, CartItem("tie clip", 0.0))
    }

    return cart

}