package model

class Location (
    var id: Int,
    val mensagem: String,
    val longitude: Double,
    val latitude: Double,
    val galaxia: String?  = "galaxia nao identificada :(",
    val planeta: String? = "planeta nao identificado :("
)

