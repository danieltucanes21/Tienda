package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PurchaseDao {
    @Insert
    suspend fun insertPurchase(purchase: Purchase)

    @Query("SELECT * FROM purchases WHERE userId = :userId")
    suspend fun getPurchasesByUser(userId: Int): List<Purchase>
}