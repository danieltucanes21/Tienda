package co.edu.unicauca.aplimovil.tienda.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PurchaseHistoryDao {
    @Insert
    suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory)

    @Query("SELECT * FROM purchase_history WHERE userId = :userId")
    suspend fun getPurchaseHistoryByUser(userId: Int): List<PurchaseHistory>
}