package co.edu.unicauca.aplimovil.tienda.data

interface ProductRepository {
    suspend fun insertProduct(product: Product)
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(productId: Int): Product?

}