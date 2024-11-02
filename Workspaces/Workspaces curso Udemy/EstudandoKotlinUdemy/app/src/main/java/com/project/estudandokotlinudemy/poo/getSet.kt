package com.project.estudandokotlinudemy.poo

class Animal2(especie : String){
    var fala = ""

        //field evita chamadas recursivas/infinitas
        get() {
            println("Acesso get")
            return field
        }

        set(value) {
            println("Acesso set")
            field = value
        }
}


fun main(){

   // Animal2("Cachorro").fala

    var animal = Animal2("Cachorro")
    animal.fala = "Gato"

}