package co.edu.unicauca.aplimovil.tienda.data


interface PurchaseRepository {
    suspend fun insertPurchase(purchase: Purchase)
    suspend fun getPurchasesByUser(userId: Int): List<Purchase>
}