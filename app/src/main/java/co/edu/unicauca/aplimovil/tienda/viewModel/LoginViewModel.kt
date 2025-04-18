package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.unicauca.aplimovil.tienda.data.UsersRepository

class LoginViewModel (private val repository: UsersRepository) : ViewModel(){


    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun updateUiState(loginDetails: LoginDetails) {
        loginUiState = LoginUiState(loginDetails = loginDetails)
    }

    suspend fun login(): Boolean {
        return repository.login(loginUiState.loginDetails.email, loginUiState.loginDetails.password)
    }
}
data class LoginUiState(
    val loginDetails: LoginDetails = LoginDetails(),
)

data class LoginDetails(
    val email: String = "",
    val password: String = ""
)
