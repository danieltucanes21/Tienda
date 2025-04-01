package edu.unicauca.apimovil.pixelplaza

import androidx.compose.ui.graphics.painter.Painter

enum class Size ()
{
    S,M,L,XL,XXL
}

enum class PublicType ()
{
    WOMEN, MEN, CHILD
}

class ProductInfo (val id: Int, val painter : Painter, val contentDescription : String, val description : String, val price : Double, val color : String, val brand : String, val sizes : List<Size>, val specifications : String, val score : Int, val publicType : PublicType) {

}