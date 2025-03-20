package co.edu.unicauca.aplimovil.tienda.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.unicauca.aplimovil.tienda.R

//import com.tu_paquete.R  // Asegúrate de importar correctamente tu archivo de recursos

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111111))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_usuario), // Asegúrate de tener el logo en res/drawable
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )

            // Texto de bienvenida
            Text("Bienvenido", fontSize = 30.sp, color = Color.White)
            Text("Inicia sesión ahora", fontSize = 16.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de correo electrónico
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de inicio de sesión
            Button(
                onClick = { /* Acción de inicio de sesión */ },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5CF0)),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Iniciar sesión", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Texto "Olvidé mi contraseña"
            ClickableText(
                text = AnnotatedString("Olvidé mi contraseña"),
                onClick = { /* Navegar a pantalla de recuperación */ },
                style = TextStyle(color = Color(0xFF7B5CF0), fontSize = 14.sp, textAlign = TextAlign.Center)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Línea divisoria
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.fillMaxWidth(0.8f))

            Spacer(modifier = Modifier.height(12.dp))

            Text("Continuar con", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // Botones de Google y Facebook centrados
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center, // Centra los iconos
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Iniciar sesión con Google */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.google_icon), // Agrega tu ícono de Google en res/drawable
                        contentDescription = "Google",
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.width(20.dp)) // Espacio entre los iconos

                IconButton(onClick = { /* Iniciar sesión con Facebook */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook), // Agrega tu ícono de Facebook en res/drawable
                        contentDescription = "Facebook",
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
