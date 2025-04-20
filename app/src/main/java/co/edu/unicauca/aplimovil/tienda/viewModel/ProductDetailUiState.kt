package co.edu.unicauca.aplimovil.tienda.viewModel

import edu.unicauca.apimovil.pixelplaza.ProductInfo

data class ProductDetailUiState(
    val product: ProductInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedSize: String? = null,
    val quantity: Int = 1,
    val showQuantityDialog: Boolean = false
)