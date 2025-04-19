package co.edu.unicauca.aplimovil.tienda.viewModel

import co.edu.unicauca.aplimovil.tienda.models.ProductCart

/**
 * Data class that represents the cart UI state
 */
data class CartUiState(
    val products: List<ProductCart> = emptyList(),
    val totalAmount: Double = 0.0,
    val itemCount: Int = 0,
    val isCheckoutProcessing: Boolean = false,
    val isCheckoutComplete: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)