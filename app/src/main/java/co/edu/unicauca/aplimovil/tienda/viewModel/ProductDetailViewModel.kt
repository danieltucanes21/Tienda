package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.data.ProductRepository
import co.edu.unicauca.aplimovil.tienda.mappers.ProductMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun loadProduct(productId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val product = productRepository.getProductById(productId.toInt())

                if (product != null) {
                    _uiState.update {
                        it.copy(
                            product = ProductMapper.toProductInfo(product),
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            product = null,
                            isLoading = false,
                            error = "Producto no encontrado"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        product = null,
                        isLoading = false,
                        error = "Error al cargar el producto: ${e.message}"
                    )
                }
            }
        }
    }

    fun updateSelectedSize(size: String) {
        _uiState.update { it.copy(selectedSize = size) }
    }

    fun updateQuantity(newQuantity: Int) {
        _uiState.update { it.copy(quantity = newQuantity.coerceAtLeast(1)) }
    }

    fun showQuantityDialog(show: Boolean) {
        _uiState.update { it.copy(showQuantityDialog = show) }
    }
}