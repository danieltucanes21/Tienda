package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credit_cards")
data class CreditCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int, // Relación con el usuario
    val cardHolder: String,
    val cardNumber: String,
    val expiryDate: String,
    val ccv: String
)