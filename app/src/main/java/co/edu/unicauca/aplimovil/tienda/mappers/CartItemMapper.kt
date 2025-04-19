package co.edu.unicauca.aplimovil.tienda.mappers

import co.edu.unicauca.aplimovil.tienda.data.CartItem
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.user
import java.util.Calendar

class CartItemMapper {
    companion object {
        fun toProductCart(product: ProductInfo, cartItem: CartItem): ProductCart {
            return ProductCart(
                idOwner = cartItem.userId,
                product = product,
                quantity = cartItem.quantity,
                selectedSize = "",
                dateAdded = java.sql.Date(Calendar.getInstance().timeInMillis)
            )
        }
    }
}