package co.edu.unicauca.aplimovil.tienda.data

class OfflineCartItemRepository(private val cartItemDao: CartItemDao) : CartItemRepository {
    override suspend fun insertCartItem(cartItem: CartItem) {
        cartItemDao.insertCartItem(cartItem)
    }

    override suspend fun getCartItemsByUser(userId: Int): List<CartItem> {
        return cartItemDao.getCartItemsByUser(userId)
    }

    override suspend fun deleteCartItem(cartItem: CartItem) {
        cartItemDao.deleteCartItem(cartItem)
    }
}