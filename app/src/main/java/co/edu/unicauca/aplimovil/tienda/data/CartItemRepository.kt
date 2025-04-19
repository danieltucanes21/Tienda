package co.edu.unicauca.aplimovil.tienda.data

interface CartItemRepository {
    suspend fun insertCartItem(cartItem: CartItem)
    suspend fun getCartItemsByUser(userId: Int): List<CartItem>
    suspend fun deleteCartItem(cartItem: CartItem)
}
