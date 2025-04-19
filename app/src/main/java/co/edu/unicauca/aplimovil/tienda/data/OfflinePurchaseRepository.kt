package co.edu.unicauca.aplimovil.tienda.data

class OfflinePurchaseRepository(private val purchaseDao: PurchaseDao) : PurchaseRepository {
    override suspend fun insertPurchase(purchase: Purchase) {
        purchaseDao.insertPurchase(purchase)
    }

    override suspend fun getPurchasesByUser(userId: Int): List<Purchase> {
        return purchaseDao.getPurchasesByUser(userId)
    }
}