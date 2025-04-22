package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.data.User
import co.edu.unicauca.aplimovil.tienda.data.UsersRepository

class RegisterViewModel (private val usersRepository: UsersRepository) : ViewModel(){

    /**
     * Holds current user ui state
     */
    // Estado interno (privado)
    private val _userUiState = mutableStateOf(UserUiState())

    // Estado expuesto a la UI (público)
    val userUiState: UserUiState
        get() = _userUiState.value

    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    // Función para actualizar los datos del usuario
    fun updateUiState(userDetails: UserDetails) {
        _userUiState.value = _userUiState.value.copy(userDetails = userDetails)
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        val user = userUiState.userDetails

        val errors = ValidationErrors(
            userNameError = if (user.userName.isBlank()) R.string.error_empty_name else null,
            emailError = when {
                user.email.isBlank() -> R.string.error_empty_email
                !android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches() -> R.string.error_invalid_email
                else -> null
            },
            passwordError = if (user.password.isBlank()) {
                R.string.error_empty_password
            } else if (user.password.length < 8) {
                R.string.error_short_password
            } else null,
            confirmPasswordError = if (user.confirmPassword.isBlank()) {
                R.string.error_empty_confirm_password
            } else if (user.confirmPassword != user.password) {
                R.string.error_password_mismatch
            } else null
        )


        // Actualizar el estado con errores
        _userUiState.value = userUiState.copy(validationErrors = errors)

        // Retornar false si algún campo tiene error
        return with(errors) {
            userNameError == null && emailError == null && passwordError == null && confirmPasswordError == null
        }
    }
    suspend fun saveUser():Boolean{
        if(validateInput()){
            usersRepository.insertUser(userUiState.userDetails.toUser())
            return true
        }
        return false
    }

}

/**
 * Represents Ui State for an User.
 */
data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false,
    val validationErrors: ValidationErrors = ValidationErrors()
)

data class UserDetails(
    val id: Int = 0,
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

data class ValidationErrors(
    val userNameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val confirmPasswordError: Int? = null
)

fun UserDetails.toUser(): User = User(
    id = id,
    userName = userName,
    email = email,
    password = password
)