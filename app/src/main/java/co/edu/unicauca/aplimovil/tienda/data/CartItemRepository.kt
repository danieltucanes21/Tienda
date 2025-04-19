package co.edu.unicauca.aplimovil.tienda.data

/*
class CartItemRepository(private val cartItemDao: CartItemDao) {
    suspend fun insertCartItem(cartItem: CartItem) = cartItemDao.insertCartItem(cartItem)
    suspend fun getCartItemsByUser(userId: Int): List<CartItem> = cartItemDao.getCartItemsByUser(userId)
    suspend fun deleteCartItem(cartItem: CartItem) = cartItemDao.deleteCartItem(cartItem)
}
*/

interface CartItemRepository {
    suspend fun insertCartItem(cartItem: CartItem)
    suspend fun getCartItemsByUser(userId: Int): List<CartItem>
    suspend fun deleteCartItem(cartItem: CartItem)
}
