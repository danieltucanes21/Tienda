package co.edu.unicauca.aplimovil.tienda.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.unicauca.apimovil.pixelplaza.textHeadlineLarge
import edu.unicauca.apimovil.pixelplaza.textLabelLarge

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Implementar búsqueda */ },
        placeholder = {
            Text(
                text = "Buscar en la tienda",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = textLabelLarge,
                fontFamily = MaterialTheme.typography.labelLarge.fontFamily)},
        leadingIcon = { Icon(
            Icons.Default.Search,
            contentDescription = "Buscar",
            tint = MaterialTheme.colorScheme.primary) },
        trailingIcon = { Icon(
            Icons.Default.AccountCircle,
            contentDescription = "Perfil",
            tint = MaterialTheme.colorScheme.primary
        ) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiary,
            unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,  // Quita la línea en estado enfocado
            unfocusedIndicatorColor = Color.Transparent, // Quita la línea en estado no enfocado
            disabledIndicatorColor = Color.Transparent  // Quita la línea en estado deshabilitad
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(50.dp)
    )
}

@Preview
@Composable
fun PreviewSearchBar () {
    SearchBar()
}