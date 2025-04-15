package co.edu.unicauca.aplimovil.tienda.viewModel

import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo

data class ShoppingUiState(
    val products: List<ProductBuy> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEmpty: Boolean = false,
    val isOnAddItem: Boolean = false
)