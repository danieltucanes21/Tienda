package co.edu.unicauca.aplimovil.tienda.mappers

import co.edu.unicauca.aplimovil.tienda.data.CartItem
import co.edu.unicauca.aplimovil.tienda.data.PurchaseHistory
import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.getActuallyDate
import edu.unicauca.apimovil.pixelplaza.getDateFromString
import edu.unicauca.apimovil.pixelplaza.getStringFromDate
import edu.unicauca.apimovil.pixelplaza.getStringFromNewDate
import java.util.Calendar

class BuyItemMapper {
    companion object {
        fun toProductBuy(product: ProductCart, buyItem: PurchaseHistory): ProductBuy {
            return ProductBuy(
                idBuy = buyItem.id,
                idCart = product.idCart,
                idOwner = buyItem.userId,
                product = product.product,
                quantity = buyItem.quantity,
                selectedSize = "",
                dateAdded = getDateFromString(buyItem.date) ?: getActuallyDate()
            )
        }

        fun toPurchaseHistory(product: CartItem): PurchaseHistory {
            return PurchaseHistory(
                id = 0,
                userId = product.userId,
                purchaseId = product.id,
                productId = product.productId,
                quantity = product.quantity,
                date = getStringFromNewDate()
            )
        }
    }
}