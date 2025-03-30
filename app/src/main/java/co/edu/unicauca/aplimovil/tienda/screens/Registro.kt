package co.edu.unicauca.aplimovil.tienda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistroScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Ícono de usuario
            Image(
                painter = painterResource(id = R.drawable.ic_usuario), // Reemplázalo con tu recurso de imagen
                contentDescription = "Usuario",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Texto de "Nueva cuenta"
            Text(
                text = "Nueva cuenta",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Descripción
            Text(
                text = "Llena el siguiente formulario con tus datos personales. Crea una contraseña y registra una cuenta de correo electrónico.",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campos de entrada
            val nombre = remember { mutableStateOf("") }
            val correo = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val confirmPassword = remember { mutableStateOf("") }

            CampoTexto("Nombre completo", nombre)
            CampoTexto("Correo electrónico", correo)
            CampoTexto("Contraseña", password, true)
            CampoTexto("Confirmar contraseña", confirmPassword, true)

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de registro
            Button(
                onClick = { /* Acción de registro */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF8A4FFF))
            ) {
                Text(text = "Crear", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

// Composable para los campos de entrada
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTexto(label: String, valor: MutableState<String>, esPassword: Boolean = false) {
    OutlinedTextField(
        value = valor.value,
        onValueChange = { valor.value = it },
        label = { Text(label, color = Color.White) },
        textStyle = TextStyle(color = Color.White),
       // visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (esPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
            .padding(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray
        )
    )
}

// Preview para ver la pantalla en Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewRegistroScreen() {
    RegistroScreen()
}
