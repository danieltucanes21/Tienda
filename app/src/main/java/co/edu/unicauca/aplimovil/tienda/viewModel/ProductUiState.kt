package co.edu.unicauca.aplimovil.tienda.viewModel

import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType


data class ProductUiState(
    val products: List<ProductInfo> = emptyList(),
    val filteredProducts: List<ProductInfo> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: PublicType? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)