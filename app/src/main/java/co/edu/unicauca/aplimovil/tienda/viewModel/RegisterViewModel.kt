package co.edu.unicauca.aplimovil.tienda.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.edu.unicauca.aplimovil.tienda.data.User
import co.edu.unicauca.aplimovil.tienda.data.UsersRepository

class RegisterViewModel (private val usersRepository: UsersRepository) : ViewModel(){

    /**
     * Holds current user ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set

    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            userName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && password == confirmPassword
        }
    }
    suspend fun saveUser(){
        if(validateInput()){
            usersRepository.insertUser(userUiState.userDetails.toUser())
        }
    }

}

/**
 * Represents Ui State for an User.
 */
data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false

)

data class UserDetails(
    val id: Int = 0,
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

/**
 * Extension function to convert [ItemDetails] to [Item]. If the value of [ItemDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemDetails.quantity] is not a valid [Int], then the quantity will be set to 0
 */
fun UserDetails.toUser(): User = User(
    id = id,
    userName = userName,
    email = email,
    password = password
)