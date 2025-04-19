package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val productId: Int,
    val quantity: Int,
    val date: String
)