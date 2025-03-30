package co.edu.unicauca.aplimovil.tienda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import co.edu.unicauca.aplimovil.tienda.ui.theme.onSurfaceVariantDark

import co.edu.unicauca.aplimovil.tienda.ui.theme.surfaceContainerLowLight
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodySmall
import edu.unicauca.apimovil.pixelplaza.textDisplaySmall
import edu.unicauca.apimovil.pixelplaza.textTitleSmall

@Composable
fun RegistroScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
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
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Texto de "Nueva cuenta"
            Text(
                text = stringResource(R.string.new_account),
                fontSize = textTitleSmall,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Descripción
            Text(
                text = stringResource(R.string.description),
                fontSize = textBodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campos de entrada
            val nombre = remember { mutableStateOf("") }
            val correo = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val confirmPassword = remember { mutableStateOf("") }

            CampoTexto("Nombre completo", nombre, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto("Correo electrónico", correo, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto("Contraseña", password, true, modifier = Modifier.padding(bottom = 10.dp))
            CampoTexto("Confirmar contraseña", confirmPassword, true, modifier = Modifier.padding(bottom = 10.dp))

            Spacer(modifier = Modifier.height(30.dp))

            // Botón de registro
            Button(
                onClick = { /* Acción de registro */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Crear", fontSize = textBodyLarge, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

// Composable para los campos de entrada
@Composable
fun CampoTexto(
     label: String,
     valor: MutableState<String>,
     esPassword: Boolean = false,
     modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = valor.value,
        onValueChange = { valor.value = it },
        label = { Text(label, color = onSurfaceVariantDark) },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (esPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        modifier = modifier
            .fillMaxWidth()
            .background(surfaceContainerLowLight, shape = RoundedCornerShape(10.dp))
            .padding(4.dp),
       
    )
}

// Preview para ver la pantalla en Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewRegistroScreen() {
    RegistroScreen()
}
