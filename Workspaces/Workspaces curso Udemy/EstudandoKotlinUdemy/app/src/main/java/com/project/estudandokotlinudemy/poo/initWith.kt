package com.project.estudandokotlinudemy.poo

class Animal() {
    var fala = ""
    var especie: String = ""

    constructor(especie: String) : this() {
        this.especie = especie
        fala = when (especie) {
            "Cachorro" -> "au"
            "Gato" -> "miau"
            else -> ""
        }
    }

    fun falar() {
        println(fala)
    }

    fun dormir() {
        println("Dormindo...")
    }
}

fun main() {
    Animal("Cachorro").falar()

    // Para instanciar um objeto vazio, use:
    val animalVazio = Animal()

    //USO DO WITH
    with(animalVazio){
        falar()
        dormir()
    }

}
