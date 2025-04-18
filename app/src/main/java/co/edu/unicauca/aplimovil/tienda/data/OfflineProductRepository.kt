package co.edu.unicauca.aplimovil.tienda.data

class OfflineProductRepository (private val productDao: ProductDao) : ProductRepository {
    override suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }
}