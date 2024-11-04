package _04_stratified_design.megamart.stratified.dom

import _04_stratified_design.megamart.stratified.cart.*
import _04_stratified_design.megamart.stratified.marketing.*
import _04_stratified_design.megamart.stratified.businessRules.*

fun addItemToCart(cart: List<CartItem>, name: String, price: Double): List<CartItem> {
    var updatedCart = addItem(cart, CartItem(name, price))

    refreshDom(updatedCart)

    return updatedCart
}

fun deleteHandler(cart: List<CartItem>, name: String) {
    val updatedCart = removeItemByName(cart, name)
    refreshDom(updatedCart)
}

fun refreshDom(cart: List<CartItem>) {
    val total = calcTotal(cart)
    setDOMCartTotal(total)
    updateDOMShippingIcons(cart)
    updateDOMTax(total)
}

fun updateDOMShippingIcons(cart: List<CartItem>) {
    val buyButtons = getDOMBuyButtons() // Accessing the DOM is an action
    for (button in buyButtons) {
        if (getsFreeShipping(addItem(cart, button.item))) {
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

fun updateDOMTax(total: Double) {
    // val tax = shoppingCartTotal * 0.13  // Reading a global variable is an action
    // By passing in the total as an explicit input, we can now calculate the tax without relying on a global variable
    println("Updated tax to ${calcTax(total)}")
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