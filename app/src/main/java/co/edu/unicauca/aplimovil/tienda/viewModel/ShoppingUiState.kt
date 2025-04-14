package co.edu.unicauca.aplimovil.tienda.viewModel

import edu.unicauca.apimovil.pixelplaza.ProductInfo

data class ShoppingUiState(
    val products: List<ProductInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEmpty: Boolean = false
)