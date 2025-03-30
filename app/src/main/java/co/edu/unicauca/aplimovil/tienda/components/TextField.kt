package co.edu.unicauca.aplimovil.tienda.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.aplimovil.tienda.RegistroScreen
import co.edu.unicauca.aplimovil.tienda.ui.theme.onSurfaceLight
import co.edu.unicauca.aplimovil.tienda.ui.theme.onSurfaceVariantDark

@Composable
fun CampoTexto(
    label: String,
    valor: MutableState<String>,
    esPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor.value,
        onValueChange = { valor.value = it },
        label = { Text(label, color = onSurfaceVariantDark, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,) },
        textStyle = TextStyle(color = onSurfaceLight, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,),

        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (esPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            focusedIndicatorColor = Color.Transparent,  // Quita la línea en estado enfocado
            unfocusedIndicatorColor = Color.Transparent, // Quita la línea en estado no enfocado
            disabledIndicatorColor = Color.Transparent  // Quita la línea en estado deshabilitad
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)

        )
}

// Preview para ver la pantalla en Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewRegistroScreen() {
    RegistroScreen()
}