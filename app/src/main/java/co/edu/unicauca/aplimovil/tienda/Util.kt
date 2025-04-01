package edu.unicauca.apimovil.pixelplaza

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import co.edu.unicauca.aplimovil.tienda.R

// Tamaños para los textos de cuerpo
val textBodyLarge = 18.sp
val textBodyMedium = 16.sp
val textBodySmall = 14.sp

// Tamaños para los textos de títulos
val textTitleLarge = 36.sp
val textTitleMedium = 34.sp
val textTitleSmall = 32.sp

// Tamaños para los textos de pantallas
val textDisplayLarge = 42.sp
val textDisplayMedium = 36.sp
val textDisplaySmall = 30.sp

// Tamaños para los textos de encabezados
val textHeadlineLarge = 42.sp
val textHeadlineMedium = 36.sp
val textHeadlineSmall = 30.sp

// Tamaños para los textos de etiquetas
val textLabelLarge = 16.sp
val textLabelMedium = 14.sp
val textLabelSmall = 10.sp

val letterSpacing = 14.sp

@Composable
fun generateData () : MutableList<ProductInfo>
{
    return mutableListOf(
        ProductInfo(
            id = 0,
            painter = painterResource(R.drawable.image_list_1), // Placeholder de imagen
            contentDescription = "Camiseta Negra",
            description = "Camiseta de algodón negra con diseño minimalista.",
            price = 199.99,
            color = "Negro",
            brand = "Nike",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL),
            specifications = "100% algodón, transpirable",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            id = 1,
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
        ),
        ProductInfo(
            id = 2,
            painter = painterResource(R.drawable.image_list_3),
            contentDescription = "Pantalón Deportivo",
            description = "Pantalón jogger cómodo para entrenamiento.",
            price = 259.99,
            color = "Gris",
            brand = "Adidas",
            sizes = listOf(Size.M, Size.L, Size.XL),
            specifications = "Tela ligera, ajuste perfecto",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            id = 3,
            painter = painterResource(R.drawable.image_list_4),
            contentDescription = "Blusa Blanca",
            description = "Blusa formal de manga larga y corte clásico.",
            price = 299.99,
            color = "Blanco",
            brand = "H&M",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL),
            specifications = "Tela fresca, ideal para oficina",
            score = 4,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            id = 4,
            painter = painterResource(R.drawable.image_list_5),
            contentDescription = "Zapatillas Running",
            description = "Zapatillas deportivas con amortiguación superior.",
            price = 499.99,
            color = "Azul",
            brand = "Nike",
            sizes = listOf(Size.S, Size.M, Size.L, Size.XL, Size.XXL),
            specifications = "Tecnología Air, suela antideslizante",
            score = 5,
            publicType = PublicType.MEN
        ),
        ProductInfo(
            id = 5,
            painter = painterResource(R.drawable.image_list_6),
            contentDescription = "Sudadera con Capucha",
            description = "Sudadera térmica con interior afelpado.",
            price = 329.99,
            color = "Negro",
            brand = "Puma",
            sizes = listOf(Size.M, Size.L, Size.XL),
            specifications = "Material térmico, bolsillo frontal",
            score = 4,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            id = 6,
            painter = painterResource(R.drawable.image_list_7),
            contentDescription = "Abrigo Largo",
            description = "Abrigo largo de invierno con botones metálicos.",
            price = 699.99,
            color = "Beige",
            brand = "Mango",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Lana y poliéster, ideal para frío intenso",
            score = 5,
            publicType = PublicType.WOMEN
        ),
        ProductInfo(
            id = 7,
            painter = painterResource(R.drawable.image_list_8),
            contentDescription = "Chaqueta Deportiva Niño",
            description = "Chaqueta impermeable para niños, con capucha.",
            price = 279.99,
            color = "Verde",
            brand = "The North Face",
            sizes = listOf(Size.S, Size.M, Size.L),
            specifications = "Resistente al agua, transpirable",
            score = 5,
            publicType = PublicType.CHILD
        )
    )
}