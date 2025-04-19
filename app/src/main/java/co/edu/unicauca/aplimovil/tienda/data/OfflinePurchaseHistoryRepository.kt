package co.edu.unicauca.aplimovil.tienda.data

class OfflinePurchaseHistoryRepository (private val purchaseHistoryDao: PurchaseHistoryDao) : PurchaseHistoryRepository {
    override suspend fun insertPurchaseHistory(purchaseHistory: PurchaseHistory) {
        purchaseHistoryDao.insertPurchaseHistory(purchaseHistory)
    }

    override suspend fun getPurchaseHistoryByUser(userId: Int): List<PurchaseHistory> {
        return purchaseHistoryDao.getPurchaseHistoryByUser(userId)
    }
}