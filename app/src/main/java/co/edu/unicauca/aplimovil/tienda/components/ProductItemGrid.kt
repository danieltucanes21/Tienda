package co.edu.unicauca.aplimovil.tienda.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.unicauca.aplimovil.tienda.R
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size
import edu.unicauca.apimovil.pixelplaza.textLabelLarge
import edu.unicauca.apimovil.pixelplaza.textLabelMedium
import edu.unicauca.apimovil.pixelplaza.textLabelSmall

@Composable
fun ProductItemGridCard(product: ProductInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box {
                    Image(
                        painter = product.painter,
                        contentDescription = product.contentDescription,
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.Bold,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = product.description,
                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.padding(4.dp))
            }
        }

        // Botón "+" solapado (mitad en imagen, mitad en precio)
        IconButton(
            onClick = { /* TODO: Agregar al carrito */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-48).dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Agregar",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}


@Preview
@Composable
fun ProductItemGridCardPreview () {
    ProductItemGridCard(
        ProductInfo(
        painter = painterResource(R.drawable.image_list_2),
        contentDescription = "Vestido Rojo",
        description = "Vestido elegante rojo con corte ajustado.",
        price = 349.99,
        color = "Rojo",
        brand = "Zara",
        sizes = listOf(Size.S, Size.M, Size.L),
        specifications = "Poliéster y elastano, ideal para eventos",
        score = 4,
        publicType = PublicType.WOMEN
    )
    )
}