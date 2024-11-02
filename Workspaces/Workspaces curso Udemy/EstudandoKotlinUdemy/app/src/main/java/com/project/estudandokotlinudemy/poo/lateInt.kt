package com.project.estudandokotlinudemy.poo

class Receita(){
    //indica que a variavel vai ser inicializada posteriormente
    lateinit var instrucoes : String

    fun geraReceita(){
        instrucoes = "2 ovos"
    }

    fun imprimeReceita(){
        if(!this::instrucoes.isInitialized){
            instrucoes = "2 ovos"
        }
    }
}

fun main(){

    var r : Receita = Receita()

    r.geraReceita()

   println(r.imprimeReceita())

}