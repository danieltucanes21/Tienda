package co.edu.unicauca.aplimovil.tienda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

//import androidx.compose.foundation.text.input.PasswordVisualTransformation

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.edu.unicauca.aplimovil.tienda.components.CampoTexto
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.RegisterViewModel

import co.edu.unicauca.aplimovil.tienda.viewModel.UserDetails
import co.edu.unicauca.aplimovil.tienda.viewModel.UserUiState
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodySmall
import edu.unicauca.apimovil.pixelplaza.user
import kotlinx.coroutines.launch

@Composable
fun RegistroScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        val coroutineScope = rememberCoroutineScope()
        RegisterBody(
            userUiState = viewModel.userUiState,
            onUserValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    val isValid = viewModel.saveUser() // Este método debería devolver `true` o `false`

                    if (isValid) {
                        Navigator.navigateTo(Screen.Login.route)
                    }
                }

            },
        )

    }
}
@Composable
fun RegisterBody(
    userUiState: UserUiState,
    onUserValueChange: (UserDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderRegistro()

        FormularioRegistro(
            userUiState = userUiState,
            onValueChange = onUserValueChange

        )

        Spacer(modifier = Modifier.height(30.dp))

        // Botón de registro

        BotonRegistro(onSaveClick)
    }

}
@Composable
fun HeaderRegistro(){
    // Ícono de usuario
    Image(
        painter = painterResource(id = R.drawable.user),
        contentDescription = stringResource(R.string.user_logo),
        modifier = Modifier.size(110.dp)
    )

    Spacer(modifier = Modifier.height(10.dp))

    Spacer(modifier = Modifier.height(10.dp))

    // Descripción
    Text(
        text = stringResource(R.string.description),
        fontSize = textBodySmall,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun FormularioRegistro(

    userUiState: UserUiState,
    modifier: Modifier = Modifier,
    onValueChange: (UserDetails) -> Unit = {},
    enabled: Boolean = true,

){

    CampoTexto(
        label =stringResource(R.string.full_name),
        valor = userUiState.userDetails.userName,
        onValueChange = {onValueChange(userUiState.userDetails.copy(userName = it))},
        modifier = Modifier.padding(bottom = 10.dp)
    )
    userUiState.validationErrors.userNameError?.let {errorResId->
        Text(
            text = stringResource(id = errorResId),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }

    CampoTexto(
        label =stringResource(R.string.email),
        valor = userUiState.userDetails.email,
        modifier = Modifier.padding(bottom = 10.dp),
        onValueChange = {onValueChange(userUiState.userDetails.copy(email = it))}
    )

    userUiState.validationErrors.emailError?.let {errorResId->
        Text(
            text = stringResource(id = errorResId),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }
    CampoTexto (
        label =stringResource(R.string.password),
        valor = userUiState.userDetails.password, true,
        modifier = Modifier.padding(bottom = 10.dp),
        onValueChange = {onValueChange(userUiState.userDetails.copy(password = it))}
    )
    userUiState.validationErrors.passwordError?.let {errorResId->
        Text(
            text = stringResource(id = errorResId),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }
    CampoTexto(
        label =stringResource(R.string.confirm_password),
        valor = userUiState.userDetails.confirmPassword, true,
        modifier = Modifier.padding(bottom = 10.dp),
        onValueChange = { onValueChange(userUiState.userDetails.copy(confirmPassword = it)) }
    )
    userUiState.validationErrors.confirmPasswordError?.let {errorResId->
        Text(
            text = stringResource(id = errorResId),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }

}

@Composable
fun BotonRegistro(
    onSaveClick: () -> Unit
) {
    Button(
        onClick = onSaveClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(R.string.create),
            fontSize = textBodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
        )
    }

}
// Preview para ver la pantalla en Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewRegistroScreen() {
    RegisterBody(userUiState = UserUiState(
        UserDetails(

        )
    ), onUserValueChange = {}, onSaveClick = {}
    )
}