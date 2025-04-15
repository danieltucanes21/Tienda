package co.edu.unicauca.aplimovil.tienda.viewModel

import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType

data class StoreUiState(
    val products: List<ProductInfo> = emptyList(),
    val filteredProducts: List<ProductInfo> = emptyList(),
    val selectedCategory: PublicType? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
) {
    // Helper property to get the current category name for UI
    val currentCategoryName: String
        get() = when(selectedCategory) {
            PublicType.WOMEN -> "Mujeres"
            PublicType.MEN -> "Hombres"
            PublicType.CHILD -> "Niños"
            null -> "Todos"
        }
}