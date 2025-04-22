package co.edu.unicauca.aplimovil.tienda.screens

import android.R.attr.digits
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
import androidx.navigation.compose.rememberNavController
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.data.CreditCardRepository
import co.edu.unicauca.aplimovil.tienda.navigation.Navigator
import co.edu.unicauca.aplimovil.tienda.navigation.Screen
import co.edu.unicauca.aplimovil.tienda.viewModel.AppViewModelProvider
import co.edu.unicauca.aplimovil.tienda.viewModel.CartViewModel
import edu.unicauca.apimovil.pixelplaza.user
import co.edu.unicauca.aplimovil.tienda.data.CreditCard
import android.widget.Toast
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasarelaScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel(factory = AppViewModelProvider.Factory),
    creditCardViewModel: co.edu.unicauca.aplimovil.tienda.viewModel.CreditCardViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val cartUiState by cartViewModel.uiState.collectAsState()
    val savedCard by creditCardViewModel.cardState.collectAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }


    var cardHolder by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var ccv by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    // Rellenar los datos una sola vez cuando se cargue la tarjeta
    LaunchedEffect(savedCard) {
        savedCard?.let {
            cardHolder = it.cardHolder
            cardNumber = it.cardNumber
            expiryDate = it.expiryDate
            ccv = it.ccv
        }
    }

    // Mostrar dialog si se completa el pago
    LaunchedEffect(cartUiState.isCheckoutComplete) {
        if (cartUiState.isCheckoutComplete) {
            showDialog = true
        }
    }
    //
    LaunchedEffect(Unit) {
        creditCardViewModel.loadCardForUser(user.id)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

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
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Text(stringResource(R.string.elija_un_m_todo_de_pago), fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.height(16.dp))

            // Métodos de pago (Iconos)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_card), // Icono de tarjeta
                        contentDescription = stringResource(R.string.tarjeta),
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cash), // Icono de efectivo
                        contentDescription = stringResource(R.string.efectivo),
                        modifier = Modifier.size(40.dp)
                    )
                }
                IconButton(onClick = { /* Acción */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_crypto), // Icono de criptomonedas
                        contentDescription = stringResource(R.string.criptomoneda),
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Campos de entrada
            CampoTexto(label = stringResource(R.string.titular_de_la_tarjeta), valor = cardHolder) { cardHolder = it }
            Spacer(modifier = Modifier.height(12.dp))
            CampoTexto(label = stringResource(R.string.n_mero_de_tarjeta), valor = cardNumber, keyboardType = KeyboardType.Number) { cardNumber = it }
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CampoTexto(
                    label = stringResource(R.string.fecha_de_vencimiento),
                    valor = expiryDate,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(0.7f)
                ) {// expiryDate = it
                        input ->
                    // Filtrar solo dígitos
                    val digitsOnly = input.filter { it.isDigit() }
                    // Formatear como MM/AA
                    val formatted = when {
                        digitsOnly.length >= 3 -> digitsOnly.take(2) + "/" + digitsOnly.drop(2).take(2)
                        else -> digitsOnly
                    }

                    if (formatted.length <= 5) {
                        expiryDate = formatted
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                CampoTexto(
                    label = stringResource(R.string.ccv),
                    valor = ccv,
                    keyboardType = KeyboardType.NumberPassword,
                    isPassword = true,
                    modifier = Modifier.weight(0.3f)
                ) { ccv = it

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Confirmar
            Button(
                onClick = {
                    if (cardHolder.isBlank() || cardNumber.length != 16 || expiryDate.length != 5 || ccv.length != 3) {
                        Toast.makeText(context,
                            context.getString(R.string.completa_todos_los_campos), Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val newCard = CreditCard(
                        userId = user.id,
                        cardHolder = cardHolder,
                        cardNumber = cardNumber,
                        expiryDate = expiryDate,
                        ccv = ccv
                    )

                    // Guardar la tarjeta desde el ViewModel
                    creditCardViewModel.saveCard(newCard)

                    // Realizar checkout
                    cartViewModel.checkoutToDisplay()

                    // Mostrar diálogo de confirmación
                    showDialog = true
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.confirmar),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp
                )
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
        label = { Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,) },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily),

        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.Transparent,  // Quita la línea en estado enfocado
            unfocusedIndicatorColor = Color.Transparent, // Quita la línea en estado no enfocado
            disabledIndicatorColor = Color.Transparent  // Quita la línea en estado deshabilitad
        ),
    )
}


@Composable
fun CheckoutCompleteDialog(onAccept: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.compra_completada)) },
        text = { Text(text = stringResource(R.string.gracias_por_tu_compra)) },
        confirmButton = {
            Button(onClick = onAccept) {
                Text(text = stringResource(R.string.aceptar))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancelar))
            }
        }
    )
}
