package com.project.estudandokotlinudemy.poo

class Pessoa(val anoNascimento: Int, var nome: String){
    var doc: String? = null

    constructor(anoNascimento: Int, nome: String, doc: String) : this(anoNascimento, nome){
        this.doc = doc;
    }

    fun dormir(){

    }

    fun acordar(){

    }
}

fun main(){

    var pessoa = Pessoa(2000, "leo")

    //this - se refere ao objeto

    pessoa.dormir()
    pessoa.acordar()

}