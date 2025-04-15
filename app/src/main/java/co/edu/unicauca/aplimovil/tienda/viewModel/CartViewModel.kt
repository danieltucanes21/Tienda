package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    // Estado centralizado
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    // Actualizar la lista de productos
    fun updateProductList(newList: List<ProductCart>) {
        _uiState.update { currentState ->
            currentState.copy(
                products = newList,
                totalAmount = newList.sumOf { it.product.price },
                itemCount = newList.size
            )
        }
    }

    // Eliminar un producto
    fun removeProduct(product: ProductCart) {
        _uiState.update { currentState ->
            val newList = currentState.products.toMutableList().apply { remove(product) }
            currentState.copy(
                products = newList,
                totalAmount = newList.sumOf { it.product.price },
                itemCount = newList.size
            )
        }
    }

    // Limpiar el carrito
    fun clearCart() {
        _uiState.update { currentState ->
            currentState.copy(
                products = emptyList(),
                totalAmount = 0.0,
                itemCount = 0
            )
        }
    }

    // Procesar el pago
    fun proceedToCheckout() {
        _uiState.update { it.copy(isCheckoutProcessing = true) }
        // Simular procesamiento
        viewModelScope.launch {
            delay(2000) // Simular tiempo de procesamiento
            _uiState.update {
                it.copy(
                    isCheckoutProcessing = false,
                    isCheckoutComplete = true
                )
            }
        }
    }

    // Resetear estado de pago
    fun resetCheckoutState() {
        _uiState.update { it.copy(isCheckoutComplete = false) }
    }

    // Añadir un nuevo producto
    fun addProduct(product: ProductCart) {
        _uiState.update { currentState ->
            val newList = currentState.products.toMutableList().apply { add(product) }
            currentState.copy(
                products = newList,
                totalAmount = newList.sumOf { it.product.price },
                itemCount = newList.size
            )
        }
    }
}