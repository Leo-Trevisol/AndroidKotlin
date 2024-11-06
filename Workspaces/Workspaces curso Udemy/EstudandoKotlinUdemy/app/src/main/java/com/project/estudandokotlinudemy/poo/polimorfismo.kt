package com.project.estudandokotlinudemy.poo

interface Funcionario{
    val salario : Float
    fun bonus() : Float
}


class Gerente(override val salario: Float) : Funcionario{
    override fun bonus(): Float {
       return salario * 0.5f
    }
}

class Analista(override val salario: Float) : Funcionario{
    override fun bonus(): Float {
        return salario * 0.3f
    }
}

fun mostraBonus(func : Funcionario){
    println(func.bonus())
}

fun main(){

        mostraBonus(Gerente(5000f))
        mostraBonus(Analista(3000f))
}