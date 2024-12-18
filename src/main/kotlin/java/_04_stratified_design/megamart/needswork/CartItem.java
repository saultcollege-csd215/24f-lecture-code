package java._04_stratified_design.megamart.needswork;

// LAYER: Business logic (cart items)
public record CartItem(String name, Double price) {
}

// The equivalent class:

// public class CartItem {
//     private String name;
//     private Double price;

//     public CartItem(String name, Double price) {
//         this.name = name;
//         this.price = price;
//     }

//     public String getName() {
//         return name;
//     }

//     public Double getPrice() {
//         return price;
//     }

//     @Override
//     public String toString() {
//         return "CartItem [name=" + name + ", price=" + price + "]";
//     }

//     @Override
//     public int hashCode() {
//         final int prime = 31;
//         int result = 1;
//         result = prime * result + ((name == null) ? 0 : name.hashCode());
//         result = prime * result + ((price == null) ? 0 : price.hashCode());
//         return result;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (obj == null)
//             return false;
//         if (getClass() != obj.getClass())
//             return false;
//         CartItem other = (CartItem) obj;
//         if (name == null) {
//             if (other.name != null)
//                 return false;
//         } else if (!name.equals(other.name))
//             return false;
//         if (price == null) {
//             if (other.price != null)
//                 return false;
//         } else if (!price.equals(other.price))
//             return false;
//         return true;
//     }

// }
