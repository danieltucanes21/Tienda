package edu.unicauca.apimovil.pixelplaza

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import co.edu.unicauca.aplimovil.tienda.R
import co.edu.unicauca.aplimovil.tienda.models.ProductBuy
import co.edu.unicauca.aplimovil.tienda.models.ProductCart
import co.edu.unicauca.aplimovil.tienda.models.User
import java.sql.Date
import java.util.Calendar

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
            //painter = painterResource(R.drawable.image_list_1), // Placeholder de imagen
            painter = "https://assets.dfb.de/uploads/000/289/444/custom_style_1_mitera_hannah-ursula.jpg?1692690626",
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
        ),
        ProductInfo(
            id = 2,
            //painter = painterResource(R.drawable.image_list_3),
            painter = "https://th.bing.com/th/id/OIP.73sQuBGcuzn0EVEmGvKlZAHaH5?w=900&h=960&rs=1&pid=ImgDetMain",
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
            //painter = painterResource(R.drawable.image_list_4),
            painter = "https://earthfullyliving.com/wp-content/uploads/2022/08/extending-clothes-life-sustainable-fashion-200x300.jpg",
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
            //painter = painterResource(R.drawable.image_list_5),
            painter = "https://images.fashionmodeldirectory.com/images/models/74993/lulu-reynolds-557589-squaremedium.jpg",
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
            //painter = painterResource(R.drawable.image_list_6),
            painter = "https://images.unsplash.com/photo-1590799822755-4ec061a6b2a5?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
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
            //painter = painterResource(R.drawable.image_list_7),
            painter = "https://i.scdn.co/image/ab67616d00001e02e254f7cd902eda979b28c012",
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
            //painter = painterResource(R.drawable.image_list_8),
            painter = "https://jm-moda.si/wp-content/uploads/2019/05/16-2-2-256x256.jpg",
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



// Función para generar una compra
fun generateBuy(user: User, cart: MutableList<ProductCart>): MutableList<ProductBuy> {
    val currentDate = Date(Calendar.getInstance().timeInMillis)
    return cart.map { productCart ->
        ProductBuy(
            idBuy = productCart.idCart,
            idOwner = user.id,
            product = productCart.product,
            quantity = productCart.quantity,
            selectedSize = productCart.selectedSize,
            dateAdded = currentDate
        )
    }.toMutableList()
}

// Datos de ejemplo para un usuario
val user = User(
    id = 1,
    email = "usuario@example.com",
    password = "password123",
    name = "Juan Pérez",
    phone = "3001234567",
    address = "Calle 123 #45-67, Bogotá"
)

// Función para generar un carrito de compras con los productos de ejemplo
@Composable
fun generateCart(user: User): MutableList<ProductCart> {
    val products = generateData()
    val currentDate = Date(Calendar.getInstance().timeInMillis)

    return mutableListOf(
        ProductCart(
            idCart = 0,
            idOwner = user.id,
            product = products[0], // Camiseta Negra
            quantity = 2,
            selectedSize = "M",
            dateAdded = currentDate
        ),
        ProductCart(
            idCart = 1,
            idOwner = user.id,
            product = products[4], // Zapatillas Running
            quantity = 1,
            selectedSize = "L",
            dateAdded = currentDate
        ),
        ProductCart(
            idCart = 2,
            idOwner = user.id,
            product = products[6], // Abrigo Largo
            quantity = 1,
            selectedSize = "S",
            dateAdded = currentDate
        )
    )
}