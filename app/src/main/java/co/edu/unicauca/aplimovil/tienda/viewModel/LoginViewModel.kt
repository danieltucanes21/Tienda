package co.edu.unicauca.aplimovil.tienda.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.unicauca.aplimovil.tienda.data.UsersRepository
import co.edu.unicauca.aplimovil.tienda.mappers.UserMapper
import co.edu.unicauca.aplimovil.tienda.models.User
import edu.unicauca.apimovil.pixelplaza.user

class LoginViewModel (private val repository: UsersRepository) : ViewModel(){


    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun updateUiState(loginDetails: LoginDetails) {
        loginUiState = LoginUiState(loginDetails = loginDetails)
    }

    suspend fun login(): Boolean {
        user = UserMapper.toUserFromUserBD(repository.login2(loginUiState.loginDetails.email, loginUiState.loginDetails.password)?: co.edu.unicauca.aplimovil.tienda.data.User(id = 0, email = "", password = "", userName = ""))
        Log.i("MyApp", "nuevos datos de usuario global: $user")
        return repository.login(loginUiState.loginDetails.email, loginUiState.loginDetails.password)
    }
}
data class LoginUiState(
    val userBD : User = user,
    val loginDetails: LoginDetails = LoginDetails(),
)

data class LoginDetails(
    val email: String = "",
    val password: String = ""
)
