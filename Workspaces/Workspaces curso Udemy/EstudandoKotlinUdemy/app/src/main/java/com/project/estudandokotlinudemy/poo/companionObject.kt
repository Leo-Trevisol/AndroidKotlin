package com.project.estudandokotlinudemy.poo

class Matematica(){

    //Static do java
    companion object{
        val PI = 3.14
        fun teste(){}

        init {
            println("Inicializado")
        }
    }

    object ob1{
        val PI = 3.14
        fun teste(){}
    }

    object ob2{
        val PI = 3.14
        fun teste(){}
    }
}


fun main(){

    Matematica.PI
    Matematica.PI
    Matematica.PI

    Matematica.teste()

    Matematica.ob1.PI
    Matematica.ob2.PI

}