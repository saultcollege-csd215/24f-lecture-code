package _04_stratified_design.megamart.stratified_with_abstraction_barrier.marketing

import _04_stratified_design.megamart.stratified_with_abstraction_barrier.cart.*

fun Cart.getsFreeShipping() = this.calcTotal() > 20.0

fun Cart.addFreeTieClip(): Cart {

    val hasTie = this.isNameInCart("tie")
    val hasTieClip = this.isNameInCart("tie clip")

    if ( hasTie && !hasTieClip ) {
        return this.addItem(CartItem("tie clip", 0.0))
    }

    return this

}