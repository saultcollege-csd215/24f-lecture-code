package java._04_stratified_design.megamart.stratified_with_abstractionbarrier;

public record CartItem(String name, double price) {

    public CartItem withPrice(double price) {
        return new CartItem(this.name, price);
    }
}