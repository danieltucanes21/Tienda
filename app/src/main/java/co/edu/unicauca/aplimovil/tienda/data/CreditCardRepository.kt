package co.edu.unicauca.aplimovil.tienda.data

interface CreditCardRepository {
    suspend fun insertCreditCard(creditCard: CreditCard)
    suspend fun getCreditCardsByUser(userId: Int): List<CreditCard>
    suspend fun updateCreditCard(creditCard: CreditCard)
    suspend fun deleteCreditCard(creditCard: CreditCard)
}