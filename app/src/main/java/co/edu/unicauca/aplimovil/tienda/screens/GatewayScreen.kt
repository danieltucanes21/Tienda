package co.edu.unicauca.aplimovil.tienda.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasarelaScreen(navController: NavHostController) {

    var cardHolder by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var ccv by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_usuario),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text("Elija un método de pago", fontSize = 20.sp, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            // Métodos de pago (Iconos)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_card), // Icono de tarjeta
                        contentDescription = "Tarjeta",
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cash), // Icono de efectivo
                        contentDescription = "Efectivo",
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_crypto), // Icono de criptomonedas
                        contentDescription = "Criptomoneda",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Campos de entrada
            CampoTexto(label = "Titular de la tarjeta", valor = cardHolder) { cardHolder = it }
            Spacer(modifier = Modifier.height(12.dp))
            CampoTexto(label = "Número de tarjeta", valor = cardNumber, keyboardType = KeyboardType.Number) { cardNumber = it }
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CampoTexto(
                    label = "Fecha de vencimiento",
                    valor = expiryDate,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(0.7f)
                ) { expiryDate = it }

                Spacer(modifier = Modifier.width(8.dp))

                CampoTexto(
                    label = "CCV",
                    valor = ccv,
                    keyboardType = KeyboardType.NumberPassword,
                    isPassword = true,
                    modifier = Modifier.weight(0.3f)
                ) { ccv = it }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Confirmar
            Button(
                onClick = { Navigator.navigateTo(Screen.Store.route) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5CF0)),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Confirmar", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

// Composable para los campos de entrada con estilos aplicados
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTexto(
    modifier: Modifier = Modifier,
    label: String,
    valor: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPasarelaScreen() {
    //PasarelaScreen(navController = NavHostController()) // Reemplaza null con un NavHostController válido si es necesario
}
