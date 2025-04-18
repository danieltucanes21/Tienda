package co.edu.unicauca.aplimovil.tienda.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import coil.compose.rememberAsyncImagePainter
import edu.unicauca.apimovil.pixelplaza.textBodyLarge
import edu.unicauca.apimovil.pixelplaza.textBodyMedium
import edu.unicauca.apimovil.pixelplaza.textBodySmall

@Composable
fun ProductItem(product: ProductInfo, modifier: Modifier = Modifier, onRemove: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            // Placeholder de la imagen
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)) // Fondo
            ) {
                Image(
                    //painter = product.painter,
                    painter = rememberAsyncImagePainter(model = product.painter),
                    contentDescription = product.contentDescription,
                    modifier = Modifier
                        .fillMaxSize(),

                    contentScale = ContentScale.Crop // Recorta para llenar el espacio
                )
            }

            // Información del producto
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.description,
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = textBodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Brand: ${product.brand}",
                    fontSize = textBodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = "Color: ${product.color}",
                    fontSize = textBodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$${product.price}",
                    fontSize = textBodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }

            // Ícono de eliminar
            IconButton(
                onClick = { onRemove() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview () {
    ProductItem(ProductInfo(
        id = 1,
        //painter = painterResource(R.drawable.image_list_2),
        painter = "https://th.bing.com/th/id/OIP.UAiTfjoZB_J1ISwqrzhgWgHaHa?w=481&h=481&rs=1&pid=ImgDetMain",
        contentDescription = "Vestido Rojo",
        description = "Vestido elegante rojo con corte ajustado.",
        price = 349.99,
        color = "Rojo",
        brand = "Zara",
        sizes = listOf(Size.S, Size.M, Size.L),
        specifications = "Poliéster y elastano, ideal para eventos",
        score = 4,
        publicType = PublicType.WOMEN
    ))
}

