package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.data.Product
import co.edu.unicauca.aplimovil.tienda.data.ProductRepository
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size
import kotlinx.coroutines.launch


class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    var productUiState by mutableStateOf(ProductUiState())
        private set

    init {
        loadProducts()
    }

    private fun loadProducts() {
        productUiState = productUiState.copy(isLoading = true)

        // Cargar productos desde Room
        viewModelScope.launch {
            try {
                val productsFromDb = productRepository.getAllProducts()
                println("Productos cargados en ViewModel: $productsFromDb") // Registro de depuración
                val productsUi = productsFromDb.map { it.toProductInfo() }

                productUiState = productUiState.copy(
                    products = productsUi,
                    filteredProducts = productsUi,
                    isLoading = false
                )
            } catch (e: Exception) {
                productUiState = productUiState.copy(
                    error = "Error cargando productos: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun search(query: String) {
        productUiState = productUiState.copy(
            searchQuery = query,
            filteredProducts = productUiState.products.filter {
                it.description.contains(query, ignoreCase = true)
            }
        )
    }

    fun filterByCategory(category: PublicType?) {
        productUiState = productUiState.copy(
            selectedCategory = category,
            filteredProducts = if (category == null) {
                productUiState.products
            } else {
                productUiState.products.filter { it.publicType == category }
            }
        )
    }
}

fun Product.toProductInfo(): ProductInfo {
    return ProductInfo(
        id = id,
        painter = imageUrl, // ahora el painter es un String URL, NO un objeto painter
        contentDescription = description,
        description = description,
        price = price.toDouble(),
        color = color,
        brand = brand,
        sizes = listOf(Size.valueOf(size.uppercase())), // ¡IMPORTANTE! convertir String a enum  o se va  a la m
        specifications = specifications,
        score = score.toIntOrNull() ?: 0, // convierte el String score a Int
        publicType = PublicType.valueOf(publicType.uppercase())
    )
}

