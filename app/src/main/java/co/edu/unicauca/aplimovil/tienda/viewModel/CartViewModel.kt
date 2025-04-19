package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.data.CartItem
import co.edu.unicauca.aplimovil.tienda.data.CartItemRepository
import co.edu.unicauca.aplimovil.tienda.data.ProductRepository
import co.edu.unicauca.aplimovil.tienda.mappers.CartItemMapper
import co.edu.unicauca.aplimovil.tienda.mappers.ProductMapper
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartItemRepository: CartItemRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    // Estado centralizado
    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    // Cargar los productos del carrito desde la base de datos
    fun loadCartItems(userId: Int) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val cartItems = cartItemRepository.getCartItemsByUser(userId)
                val productCarts = cartItems.mapNotNull { cartItem ->
                    val product = productRepository.getProductById(cartItem.productId)
                    product?.let {
                        CartItemMapper.toProductCart(
                            ProductMapper.toProductInfo(it),
                            cartItem
                        )
                    }
                }

                _uiState.update {
                    it.copy(
                        products = productCarts,
                        totalAmount = productCarts.sumOf { it.product.price * it.quantity },
                        itemCount = productCarts.sumOf { it.quantity },
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Error loading cart items: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    // Añadir un nuevo producto al carrito
    fun addProduct(productCart: ProductCart) {
        viewModelScope.launch {
            try {
                val cartItem = CartItem(
                    userId = productCart.idOwner,
                    productId = productCart.product.id,
                    quantity = productCart.quantity
                )
                cartItemRepository.insertCartItem(cartItem)

                _uiState.update { currentState ->
                    val newList = currentState.products.toMutableList().apply { add(productCart) }
                    currentState.copy(
                        products = newList,
                        totalAmount = newList.sumOf { it.product.price * it.quantity },
                        itemCount = newList.sumOf { it.quantity }
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Error adding product: ${e.message}")
                }
            }
        }
    }

    // Eliminar un producto del carrito
    fun removeProduct(product: ProductCart) {
        viewModelScope.launch {
            try {
                val cartItem = CartItem(
                    userId = product.idOwner,
                    productId = product.product.id,
                    quantity = product.quantity
                )
                cartItemRepository.deleteCartItem(cartItem)

                _uiState.update { currentState ->
                    val newList = currentState.products.toMutableList().apply { remove(product) }
                    currentState.copy(
                        products = newList,
                        totalAmount = newList.sumOf { it.product.price * it.quantity },
                        itemCount = newList.sumOf { it.quantity }
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Error removing product: ${e.message}")
                }
            }
        }
    }

    // Limpiar el carrito
    fun clearCart(userId: Int) {
        viewModelScope.launch {
            try {
                val userCartItems = cartItemRepository.getCartItemsByUser(userId)
                userCartItems.forEach { cartItemRepository.deleteCartItem(it) }

                _uiState.update {
                    it.copy(
                        products = emptyList(),
                        totalAmount = 0.0,
                        itemCount = 0
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Error clearing cart: ${e.message}")
                }
            }
        }
    }

    // Procesar el pago
    fun proceedToCheckout(userId: Int) {
        _uiState.update { it.copy(isCheckoutProcessing = true) }

        viewModelScope.launch {
            try {
                delay(2000) // Simular tiempo de procesamiento

                // Limpiar el carrito después del pago exitoso
                clearCart(userId)

                _uiState.update {
                    it.copy(
                        isCheckoutProcessing = false,
                        isCheckoutComplete = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isCheckoutProcessing = false,
                        error = "Checkout failed: ${e.message}"
                    )
                }
            }
        }
    }

    // Resetear estado de pago
    fun resetCheckoutState() {
        _uiState.update { it.copy(isCheckoutComplete = false) }
    }
}