package com.project.estudandokotlinudemy.poo

class Forma(val a : Int, val b: Int){
    override fun equals(other: Any?): Boolean {
        return if(other is Forma){
            (other.a == this.a && other.b == this.b)
            return true
        }else
            false
    }

}

data class FormaData(val a : Int, val b: Int){
//data class vem vem com os metodos hashcode, equal e tostring prontos
}

fun main(){

    var f1 : Forma = Forma(10,20)
    var f2 : FormaData = FormaData(10,20)

    println(f1.toString())
    println(f1.hashCode())
    println("--------------")
    println(f2.toString())
    println(f2.hashCode())

    var f3 = f2.copy()

}
