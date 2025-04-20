package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.unicauca.aplimovil.tienda.data.CreditCard
import co.edu.unicauca.aplimovil.tienda.data.CreditCardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreditCardViewModel(private val repository: CreditCardRepository) : ViewModel() {

    private val _cardState = MutableStateFlow<CreditCard?>(null)
    val cardState: StateFlow<CreditCard?> = _cardState.asStateFlow()

    fun loadCardForUser(userId: Int) {
        viewModelScope.launch {
            val cards = repository.getCreditCardsByUser(userId)
            if (cards.isNotEmpty()) {
                _cardState.value = cards.first()
            }
        }
    }

    fun saveCard(card: CreditCard) {
        viewModelScope.launch {
            repository.insertCreditCard(card)
            _cardState.value = card
        }
    }
}
