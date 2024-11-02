package com.project.estudandokotlinudemy.poo

enum class Prioridade(val id : Int){
    Baixa(1){
        override fun toString(): String {
            return "Prioridade $id é baixa"
        }
            },
    Media(2),
    Alta(3)
}

fun main(){

    for(n in Prioridade.values()){
        println(n)
    }

}