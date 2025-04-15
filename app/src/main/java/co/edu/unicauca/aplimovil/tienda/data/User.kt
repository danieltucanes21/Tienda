package co.edu.unicauca.aplimovil.tienda.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val password: Double,
    val userName: String,
    )