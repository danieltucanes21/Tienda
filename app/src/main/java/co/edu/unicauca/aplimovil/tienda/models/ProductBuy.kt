package co.edu.unicauca.aplimovil.tienda.models

import edu.unicauca.apimovil.pixelplaza.ProductInfo
import java.sql.Date

data class ProductBuy (val idBuy: Int, val idCart: Int = 0, val idOwner: Int, val product: ProductInfo, val quantity: Int, val selectedSize: String, val dateAdded: Date) {
}
