package com.project.estudandokotlinudemy.poo

//Open indica que uma classe ou uma função pode ser estendida ou sobrescrita
open class Eletronico(marca : String){
    open fun ligar() {}

   open fun desligar() {}


}

private class Estudo(){

}


class Computador(marca : String) : Eletronico(marca){

    fun save(){

    }

    fun save(a : Int){

    }

    fun save(a : Boolean){

    }

    override fun ligar() {
        save()
        super.ligar()
    }

    override fun desligar() {
        super.desligar()
    }


}


fun main(){

    var e = Eletronico("Celular")
    e.ligar()
    //e.desligar()


}