package co.edu.unicauca.aplimovil.tienda.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.unicauca.aplimovil.tienda.R
import androidx.compose.material3.MaterialTheme as MaterialTheme1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp), // Aumenté el padding horizontal
        verticalArrangement = Arrangement.spacedBy(24.dp), // Aumenté el espaciado
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // Espacio arriba del primer card

        // Card Desarrollado por
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), // Padding horizontal interno
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Más elevación
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp) // Más padding interno
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp), // Más espaciado
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.desarrollado_por),
                    style = MaterialTheme1.typography.titleLarge.copy( // Tamaño más grande
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme1.colorScheme.primary, // Color primario
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 4.dp),
                    color = MaterialTheme1.colorScheme.outlineVariant,
                    thickness = 1.dp
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.juan_manquillo),
                        style = MaterialTheme1.typography.titleMedium, // Tamaño mediano
                        color = MaterialTheme1.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.daniel_tucanes),
                        style = MaterialTheme1.typography.titleMedium,
                        color = MaterialTheme1.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = stringResource(R.string.steven_chanchi),
                        style = MaterialTheme1.typography.titleMedium,
                        color = MaterialTheme1.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Card Descripción
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.descripcion),
                    style = MaterialTheme1.run {
                        typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    },
                    color = MaterialTheme1.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(vertical = 4.dp),
                    color = MaterialTheme1.colorScheme.outlineVariant,
                    thickness = 1.dp
                )

                Text(
                    text = stringResource(R.string.descripcion_app),
                    style = MaterialTheme1.typography.bodyLarge, // Tamaño más grande
                    color = MaterialTheme1.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp // Altura de línea aumentada
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espacio al final
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScreenAboutPreview() {
//    MaterialTheme1 {
//        AboutScreen()
//    }
//}