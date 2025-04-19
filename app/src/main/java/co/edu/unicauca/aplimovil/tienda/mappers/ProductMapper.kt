// ProductMappers.kt
package co.edu.unicauca.aplimovil.tienda.mappers

import co.edu.unicauca.aplimovil.tienda.data.Product
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size

class ProductMapper {
    companion object {
        fun toProductInfo(product: Product): ProductInfo {
            return ProductInfo(
                id = product.id,
                painter = product.imageUrl,
                contentDescription = product.description,
                description = product.description,
                price = product.price.toDouble(),
                color = product.color,
                brand = product.brand,
                sizes = listOf(Size.valueOf(product.size.uppercase())),
                specifications = product.specifications,
                score = product.score.toIntOrNull() ?: 0,
                publicType = PublicType.valueOf(product.publicType.uppercase())
            )
        }
    }
}