package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.lifecycle.ViewModel
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StoreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    // Initialize with products
    fun initialize(products: List<ProductInfo>) {
        _uiState.update {
            it.copy(
                products = products,
                filteredProducts = products
            )
        }
    }

    // Filter products by category
    fun filterByCategory(category: PublicType?) {
        _uiState.update { currentState ->
            val filtered = if (category == null) {
                currentState.products
            } else {
                currentState.products.filter { it.publicType == category }
            }

            currentState.copy(
                selectedCategory = category,
                filteredProducts = filtered
            )
        }
    }

    // Search products
    fun search(query: String) {
        _uiState.update { currentState ->
            val filtered = if (query.isBlank()) {
                currentState.products
            } else {
                currentState.products.filter {
                    it.description.contains(query, ignoreCase = true) ||
                            it.brand.contains(query, ignoreCase = true) ||
                            it.color.contains(query, ignoreCase = true)
                }
            }

            currentState.copy(
                searchQuery = query,
                filteredProducts = filtered
            )
        }
    }

    // Clear search
    fun clearSearch() {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = "",
                filteredProducts = currentState.products
            )
        }
    }
}