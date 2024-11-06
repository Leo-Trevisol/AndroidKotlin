package com.project.estudandokotlinudemy.poo

interface Event{
    fun onSucess()
}

class Program(){
    fun salvar(e : Event){
        println("Abrindo conexões")
        e.onSucess()
    }
}

fun main(){
    val p = Program()
    p.salvar(object  : Event {
        override fun onSucess() {

            print("OnSucess")
        }

    })
}