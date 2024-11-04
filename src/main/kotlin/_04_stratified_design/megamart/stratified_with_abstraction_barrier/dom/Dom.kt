package _04_stratified_design.megamart.stratified_with_abstraction_barrier.dom

import _04_stratified_design.megamart.stratified_with_abstraction_barrier.cart.*
import _04_stratified_design.megamart.stratified_with_abstraction_barrier.marketing.getsFreeShipping

fun addItemToCart(cart: Cart, name: String, price: Double) {
    val updatedCart = cart.addItem(CartItem(name, price))
    refreshDom(updatedCart)
}

fun deleteHandler(cart: Cart, name: String) {
    val updatedCart = cart.removeItemByName(name)
    refreshDom(updatedCart)
}

fun refreshDom(cart: Cart) {
    val total = cart.calcTotal()
    setDOMCartTotal(total)
    updateDOMShippingIcons(cart)
    updateDOMTax(cart.tax())
}

fun updateDOMShippingIcons(cart: Cart) {
    val buyButtons = getDOMBuyButtons() // Accessing the DOM is an action
    for (button in buyButtons) {
        if (cart.addItem(button.item).getsFreeShipping()) {
            println("Free shipping icon shown on ${button.item.name}")
        } else {
            println("Free shipping icon hidden on ${button.item.name}")
        }
    }
}

fun setDOMCartTotal(total: Double) {
    // Update the total in the DOM (an action)
    // e.g. document.getElementById("cart-total").innerText = total
    // To keep things simple here, we'll just print the change
    println("Updated cart total to $total")
}

fun updateDOMTax(tax: Double) {
    // val tax = shoppingCartTotal * 0.13  // Reading a global variable is an action
    // By passing in the total as an explicit input, we can now calculate the tax without relying on a global variable
    println("Updated tax to $tax")
}

data class BuyButton(val item: CartItem)
fun getDOMBuyButtons() =
// NOTE: This is just a simulation of something like
// document.getElementsByClassName("buy-button")

    // To keep things simple here, we just simulate with a list of 3 buttons
    listOf(
        BuyButton(CartItem("Item 1", 10.0)),
        BuyButton(CartItem("Item 2", 15.0)),
        BuyButton(CartItem("Item 3", 5.0))
    )