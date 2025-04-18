package co.edu.unicauca.aplimovil.tienda.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.FormularioRegistro
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.RegisterBody
import co.edu.unicauca.aplimovil.tienda.components.CampoTexto
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.LoginDetails
import co.edu.unicauca.aplimovil.tienda.viewModel.LoginUiState
import co.edu.unicauca.aplimovil.tienda.viewModel.LoginViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.RegisterViewModel
import co.edu.unicauca.aplimovil.tienda.viewModel.UserDetails
import co.edu.unicauca.aplimovil.tienda.viewModel.UserUiState
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodyMedium
import edu.unicauca.apimovil.pixelplaza.textBodySmall
import edu.unicauca.apimovil.pixelplaza.textTitleSmall
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val coroutineScope = rememberCoroutineScope()

            var loginError by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(16.dp))

            LoginBody(
                loginUiState = viewModel.loginUiState,
                onLoginValueChange = viewModel::updateUiState,
                onLoginClick = {
                    coroutineScope.launch {
                        val loginSuccess = viewModel.login()
                        if (loginSuccess) {
                            loginError = false
                            Navigator.navigateTo(Screen.Store.route)
                        } else {
                            loginError = true
                        }
                    }
                },
                loginError = loginError
            )

        }
    }
}
@Composable
fun LoginBody(
    loginUiState: LoginUiState,
    onLoginValueChange: (LoginDetails) -> Unit,
    onLoginClick: () -> Unit,
    loginError: Boolean = false
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.main_logo),
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        WelcomeText()
        Spacer(modifier = Modifier.height(12.dp))

        if (loginError) {
            Text(
                text = stringResource(R.string.correo_o_contrase_a_incorrectos),
                color = MaterialTheme.colorScheme.error,
                fontSize = textBodyMedium,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        LoginForm(
            loginDetails = loginUiState.loginDetails,
            onValueChange = onLoginValueChange

        )
        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(onLoginClick)

        Spacer(modifier = Modifier.height(12.dp))
        // Texto "Olvidé mi contraseña"
        Text(
            text = stringResource(R.string.forgot_my_password),
            fontSize = textBodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,

            )


        Spacer(modifier = Modifier.height(12.dp))
        LoginWithButtons()
        Spacer(modifier = Modifier.height(12.dp))
        SignUpText()
    }


}
@Composable
fun WelcomeText(){
    // Texto de bienvenida
    Text(stringResource(R.string.welcome),
        fontSize = textTitleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,

        )
    Text(
        text = stringResource(R.string.sign_in_now),
        fontSize = textBodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,

        )
}

@Composable
fun LoginForm(
    loginDetails: LoginDetails,
    onValueChange: (LoginDetails) -> Unit = {}
){

    CampoTexto(
        label = stringResource(R.string.email),
        valor = loginDetails.email,
        modifier = Modifier.padding(bottom = 10.dp),
        onValueChange = {onValueChange(loginDetails.copy(email = it))},
    )

    CampoTexto(
        label =stringResource(R.string.password),
        valor = loginDetails.password,true,
        modifier = Modifier.padding(bottom = 10.dp),
        onValueChange = {onValueChange(loginDetails.copy(password = it))}
    )
}

@Composable
fun LoginButton(
    onLoginClick: () -> Unit
){
    // Botón de inicio de sesión
    Button(
        onClick =  onLoginClick/*Navigator.navigateTo(Screen.Store.route)*/ ,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(stringResource(R.string.sign_in),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = textBodyLarge,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        )


    }

}

@Composable
fun SignUpText(){
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.dont_have_an_account),
            fontSize = textBodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,

            )
        TextButton(
            onClick = { Navigator.navigateTo(Screen.SignUp.route) },
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                color = MaterialTheme.colorScheme.primary,
                fontSize = textBodyMedium,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            )
        }
    }


}

@Composable
fun LoginWithButtons(){
    
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = stringResource(R.string.continue_with),
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = textBodySmall,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
    )
    Spacer(modifier = Modifier.height(12.dp))
    // Botones de Google y Facebook centrados
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Iniciar sesión con Google */ }) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = stringResource(R.string.google_logo),
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        IconButton(onClick = { /* Iniciar sesión con Facebook */ }) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = stringResource(R.string.facebook_logo),
                modifier = Modifier.size(40.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginBody(loginUiState = LoginUiState(
        LoginDetails(

        )
    ), onLoginClick = {}, onLoginValueChange = {})
}
