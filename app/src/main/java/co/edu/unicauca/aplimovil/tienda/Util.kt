package edu.unicauca.apimovil.pixelplaza

fun processText (text : String, maxChar : Int = 40) : String {
    return if (text.length <= maxChar) {
        text // Si el texto tiene menos o igual a 44 caracteres, lo devuelve tal cual.
    } else {
        text.take(maxChar) + "..." // Si tiene más de 44 caracteres, toma los primeros 44 y agrega "..."
    }
}