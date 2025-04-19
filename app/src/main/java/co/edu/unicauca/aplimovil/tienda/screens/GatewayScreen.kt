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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import edu.unicauca.apimovil.pixelplaza.user

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasarelaScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val cartUiState by cartViewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(cartUiState.isCheckoutComplete) {
        if (cartUiState.isCheckoutComplete) {
            showDialog = true
        }
    }

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
                onClick = {
                    cartViewModel.checkoutToDisplay()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5CF0)),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Confirmar", color = Color.White, fontSize = 18.sp)
            }

            if (showDialog) {
                CheckoutCompleteDialog(
                    onAccept = {
                        cartViewModel.proceedToCheckout(userId = user.id)
                        showDialog = false
                        cartViewModel.resetCheckoutState()
                        navController.navigate(Screen.Store.route) // Usar navController directamente
                    },
                    onDismiss = {
                        showDialog = false
                        cartViewModel.resetCheckoutState()
                    }
                )
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


@Composable
fun CheckoutCompleteDialog(onAccept: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Compra completada") },
        text = { Text(text = "¡Gracias por tu compra!") },
        confirmButton = {
            Button(onClick = onAccept) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPasarelaScreen() {
    //PasarelaScreen(navController = NavHostController()) // Reemplaza null con un NavHostController válido si es necesario
}
