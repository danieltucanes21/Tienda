package co.edu.unicauca.aplimovil.tienda.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.components.CampoTexto
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodyMedium
import edu.unicauca.apimovil.pixelplaza.textBodySmall
import edu.unicauca.apimovil.pixelplaza.textTitleSmall



@Composable
fun LoginScreen() {


    val correo = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
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
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.main_logo),
                modifier = Modifier.size(180.dp)
            )

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

            Spacer(modifier = Modifier.height(16.dp))



            CampoTexto(
                label = stringResource(R.string.email),
                valor = correo,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            CampoTexto(
                label =stringResource(R.string.password),
                valor = password,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de inicio de sesión
            Button(
                onClick = { /* Acción de inicio de sesión */ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text(stringResource(R.string.sign_in),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = textBodyLarge,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                )


            }

            Spacer(modifier = Modifier.height(12.dp))

            // Texto "Olvidé mi contraseña"
            Text(
                text = stringResource(R.string.forgot_my_password),
                fontSize = textBodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,

            )


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
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
