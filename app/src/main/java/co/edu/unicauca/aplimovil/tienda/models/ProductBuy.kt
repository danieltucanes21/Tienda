package co.edu.unicauca.aplimovil.tienda.models

import edu.unicauca.apimovil.pixelplaza.ProductInfo
import java.sql.Date

class ProductBuy (val idOwner: Int, val product: ProductInfo, val quantity: Int, val selectedSize: String, val dateAdded: Date) {
}
