package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface CreditCardDao {
    @Insert
    suspend fun insertCreditCard(creditCard: CreditCard)

    @Query("SELECT * FROM credit_cards WHERE userId = :userId")
    suspend fun getCreditCardsByUser(userId: Int): List<CreditCard>

    @Update
    suspend fun updateCreditCard(creditCard: CreditCard)

    @Delete
    suspend fun deleteCreditCard(creditCard: CreditCard)
}