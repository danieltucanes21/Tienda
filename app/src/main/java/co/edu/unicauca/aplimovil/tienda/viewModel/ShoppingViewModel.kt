package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.data.CartItem
import co.edu.unicauca.aplimovil.tienda.data.ProductRepository
import co.edu.unicauca.aplimovil.tienda.data.PurchaseHistory
import co.edu.unicauca.aplimovil.tienda.data.PurchaseHistoryRepository
import co.edu.unicauca.aplimovil.tienda.mappers.BuyItemMapper
import co.edu.unicauca.aplimovil.tienda.mappers.CartItemMapper
import co.edu.unicauca.aplimovil.tienda.mappers.ProductMapper
import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val purchaseHistoryRepository: PurchaseHistoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingUiState())
    val uiState: StateFlow<ShoppingUiState> = _uiState.asStateFlow()

    // Load purchase history for a user
    fun loadProducts(userId: Int) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val purchaseHistoryItems = purchaseHistoryRepository.getPurchaseHistoryByUser(userId)
                val productBuys = purchaseHistoryItems.mapNotNull { purchaseItem ->
                    val product = productRepository.getProductById(purchaseItem.productId)
                    product?.let {
                        BuyItemMapper.toProductBuy(
                            CartItemMapper.toProductCart(
                                ProductMapper.toProductInfo(it),
                                CartItem(
                                    id = purchaseItem.purchaseId,
                                    userId = userId,
                                    productId = purchaseItem.productId,
                                    quantity = purchaseItem.quantity
                                )
                            ),
                            purchaseItem
                        )
                    }
                }

                _uiState.update {
                    it.copy(
                        products = productBuys,
                        isLoading = false,
                        isEmpty = productBuys.isEmpty(),
                        error = if (productBuys.isEmpty()) "No hay compras realizadas" else null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error loading purchase history: ${e.message}"
                    )
                }
            }
        }
    }

    // Refresh products
    fun refresh(userId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        loadProducts(userId)
    }

    fun removeProduct(product: ProductBuy) {
        viewModelScope.launch {
            try {
                val purchaseItem = PurchaseHistory(
                    id = product.idBuy,
                    purchaseId = product.idCart,
                    userId = product.idOwner,
                    productId = product.product.id,
                    quantity = product.quantity,
                    date = product.dateAdded.toString()
                )
                purchaseHistoryRepository.deletePurchaseHistory(purchaseItem)

                _uiState.update { currentState ->
                    val newList = currentState.products.toMutableList().apply { remove(product) }
                    currentState.copy(
                        products = newList,
                        isEmpty = newList.isEmpty()
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Error removing product: ${e.message}")
                }
            }
        }
    }
}