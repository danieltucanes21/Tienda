package co.edu.unicauca.aplimovil.tienda.data

class OfflineCreditCardRepository(private val creditCardDao: CreditCardDao) : CreditCardRepository {
    override suspend fun insertCreditCard(creditCard: CreditCard) {
        creditCardDao.insertCreditCard(creditCard)
    }

    override suspend fun getCreditCardsByUser(userId: Int): List<CreditCard> {
        return creditCardDao.getCreditCardsByUser(userId)
    }

    override suspend fun updateCreditCard(creditCard: CreditCard) {
        creditCardDao.updateCreditCard(creditCard)
    }

    override suspend fun deleteCreditCard(creditCard: CreditCard) {
        creditCardDao.deleteCreditCard(creditCard)
    }
}