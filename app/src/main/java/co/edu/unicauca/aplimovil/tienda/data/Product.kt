package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    //@PrimaryKey(autoGenerate = true) val id: Int = 0,
    @PrimaryKey val id: Int,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val size: String,
    val score: String,
    val color: String,
    val specifications: String,
    val brand: String,
    val publicType: String
)