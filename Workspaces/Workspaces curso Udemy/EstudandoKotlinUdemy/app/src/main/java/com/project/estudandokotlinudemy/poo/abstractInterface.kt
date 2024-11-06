package com.project.estudandokotlinudemy.poo

interface Selvagem{
    fun atacar()
}

abstract class Mamifero(val nome : String){
    abstract fun falar()
}

class Cachorro(nome : String) : Mamifero(nome), Selvagem{
    override fun falar() {
    }

    override fun atacar() {

    }

}

fun main(){
    //val m = Mamifero()
}
