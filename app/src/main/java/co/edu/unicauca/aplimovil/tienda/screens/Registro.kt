package co.edu.unicauca.aplimovil.tienda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import co.edu.unicauca.aplimovil.tienda.ui.theme.surfaceLight
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodySmall

import edu.unicauca.apimovil.pixelplaza.textTitleSmall

@Composable
fun RegistroScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceLight)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícono de usuario
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Usuario",
                modifier = Modifier.size(110.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Texto de "Nueva cuenta"
            Text(
                text = stringResource(R.string.new_account),
                fontSize = textTitleSmall,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            )

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

            // Campos de entrada
            val nombre = remember { mutableStateOf("") }
            val correo = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val confirmPassword = remember { mutableStateOf("") }

           CampoTexto(stringResource(R.string.full_name), nombre, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto(stringResource(R.string.email),correo, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto (stringResource(R.string.password), password, true, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto(stringResource(R.string.confirm_password), confirmPassword, true, modifier = Modifier.padding(bottom = 10.dp))

            Spacer(modifier = Modifier.height(30.dp))

            // Botón de registro
            Button(
                onClick = { /* Acción de registro */ },
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
    }
}

// Composable para los campos de entrada


// Preview para ver la pantalla en Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewRegistroScreen() {
    RegistroScreen()
}
