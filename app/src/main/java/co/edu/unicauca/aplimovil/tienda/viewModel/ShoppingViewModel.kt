package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingUiState())
    val uiState: StateFlow<ShoppingUiState> = _uiState.asStateFlow()

    // Initialize with products
    fun loadProducts(products: List<ProductBuy>) {
        _uiState.update {
            it.copy(
                products = products,
                isLoading = false,
                isEmpty = products.isEmpty(),
                error = if (products.isEmpty()) "No hay compras realizadas" else null
            )
        }
    }

    // Refresh products
    fun refresh() {
        _uiState.update { it.copy(isLoading = true) }
        // Here you would typically fetch from database or API
        // For now we'll just simulate loading
        viewModelScope.launch {
            delay(1000) // Simulate network delay
            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }
        }
    }

    fun removeProduct(product: ProductBuy) {
        _uiState.update { currentState ->
            val newList = currentState.products.toMutableList().apply { remove(product) }
            currentState.copy(
                products = newList
            )
        }
    }
}