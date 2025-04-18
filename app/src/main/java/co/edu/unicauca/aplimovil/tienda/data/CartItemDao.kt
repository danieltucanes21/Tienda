package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartItemDao {
    @Insert
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    suspend fun getCartItemsByUser(userId: Int): List<CartItem>

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)
}