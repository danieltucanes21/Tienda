package co.edu.unicauca.aplimovil.tienda.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val usersRepository: UsersRepository
    val productRepository: ProductRepository
    val purchaseRepository: PurchaseRepository
    val purchaseHistoryRepository: PurchaseHistoryRepository
    val cartItemRepository: CartItemRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [UsersRepository]
     */
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    override val usersRepository: UsersRepository by lazy {
        OfflineUsersRepository(database.userDao())
    }
    override val productRepository: ProductRepository by lazy {
        OfflineProductRepository(database.productDao())
    }
    override val purchaseRepository: PurchaseRepository by lazy {
        OfflinePurchaseRepository(database.purchaseDao())
    }
    override val purchaseHistoryRepository: PurchaseHistoryRepository by lazy {
        OfflinePurchaseHistoryRepository(database.purchaseHistoryDao())
    }
    override val cartItemRepository: CartItemRepository by lazy {
        OfflineCartItemRepository(database.cartItemDao())
    }

}