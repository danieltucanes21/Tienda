package co.edu.unicauca.aplimovil.tienda.data

interface PurchaseHistoryRepository {
    suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory)
    suspend fun getPurchaseHistoryByUser(userId: Int): List<PurchaseHistory>
    suspend fun deletePurchaseHistory(purchaseHistory: PurchaseHistory)
}