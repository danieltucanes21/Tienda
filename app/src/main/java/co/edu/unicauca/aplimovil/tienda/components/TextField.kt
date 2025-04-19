package co.edu.unicauca.aplimovil.tienda.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.aplimovil.tienda.RegistroScreen

@Composable
fun CampoTexto(
    label: String,
    valor: String,
    esPassword: Boolean = false,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,

    ) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = { Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,) },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant, fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,),

        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (esPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
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