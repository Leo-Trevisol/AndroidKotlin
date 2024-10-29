package com.project.estudandokotlinudemy

import java.lang.Double.max
import java.lang.Double.min
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round

fun main() {
    println("Teste")

    var nome = "leo" //declaracao de variavel

    var nome1: String = "leo" //declaracao de variavel com tipo (não necessario na maioria dos casos)

    println(nome + " Igual a " + nome1)

    /**
     * Type   Byte
     * Double 64
     * Float  32
     * Long   64
     * Int    32
     * Short  16
     * Byte   8
     * Boolean ?
     * String  ?
     * Char    ?
     */

    println("Double max value: " + Double.MAX_VALUE + " - Double min value: " + Double.MIN_VALUE)
    println("Float max value: " + Float.MAX_VALUE + " - Float min value: " + Float.MIN_VALUE)
    println("Long max value: " + Long.MAX_VALUE + " - Long min value: " + Long.MIN_VALUE)
    println("Int max value: " + Int.MAX_VALUE + " - Int min value: " + Int.MIN_VALUE)
    println("Short max value: " + Short.MAX_VALUE + " - Short min value: " + Short.MIN_VALUE)
    println("Byte max value: " + Byte.MAX_VALUE + " - Byte min value: " + Byte.MIN_VALUE)

    var char = 'c'
    var string = "String"
    var boolean = true
    var double = 10.10
    var float = 10f
    var long = 10L
    var int = 10
    var short: Short = 10
    var byte: Byte = 10

    //Variaveis Unsigned - só podem ser positivas

    println("ULong max value: " + ULong.MAX_VALUE + " - ULong min value: " + ULong.MIN_VALUE)
    println("UInt max value: " + UInt.MAX_VALUE + " - UInt min value: " + UInt.MIN_VALUE)
    println("UShort max value: " + UShort.MAX_VALUE + " - UShort min value: " + UShort.MIN_VALUE)
    println("UByte max value: " + UByte.MAX_VALUE + " - UByte min value: " + UByte.MIN_VALUE)

    //Variavel imutavel

    val nome2 = "leo"

    //nome2 = "leo2"

    var nome3 = "leo"
    var sobrenome = "trevisol"

    println("Nome é $nome3 e sobrenome é $sobrenome")

    println("Nome é ${nome3} e sobrenome é ${sobrenome}")

    println("Nome é " + nome3 + " e sobrenome é " + sobrenome)

    println(helloWorld(nome))

    helloWorld()

    println("Tamanho do nome é de ${nome.length}")

    println("Primeira letra do nome é ${nome[0]}")

    println("Nome começa com l ? ${nome.startsWith("l")}")

    println("Nome termina com l ? ${nome.endsWith("l")}")

    nome = "leonardo"

    println("Meio do nome = ${nome.substring(4)} ")

    println("Maiscula ${nome.uppercase()}")

    println(" Minuscula ${nome.lowercase()}")

    println("O maior entre 5 e 10 é ${max(5,10)}")
    println("O menor entre 5 e 10 é ${min(5,10)}")
    println("Arredondando 15,55 = ${round(15.55f)}")

    var n = 51

    if(n >= 1 && n <= 50){

    }

    //Simplificando condição

    if(n in 1..50){

    }

    when(n){
        1 -> println("Numero = 1")
        1 -> println("Numero = 1")
        1 -> println("Numero = 1")
        1 -> println("Numero = 1")
        else -> println("Numero desconhecido")
    }

    if (1 == 2){
        print("Informe um valor: ")
        var s = readLine()

        if(s != null && !s.isEmpty()){
            println("Informou ${s}")
        }
    }

    //FOR

    for(i in 1..10)
        print("$i ")

    val str = "Leonardo"
    for(char in str)
        print("$char ")

    for(i in 1..10 step 2)
        print("$i ")

    for(i in 20 downTo 0)
        print("$i ")

    for(i in 30 downTo 0 step 3)
        print("$i ")

    //ACEITAR VARIAVEL NULA

    var s : String? = null

    println(s?.length)

    //ASSUME QUE PODE SER NULA
    println(s!!.length)

    try {
        var i : Int = 10/0;
    }catch (e : Exception){
        println("Erro generico!")
    }

    //OPERADOR ELVIS

    val str2 :String? = null
    println(str ?: "null")

    //LET - VAI EXECUTAR O BLOCO QUANDO FOR != NULL

    str2?.let {
        it.length
        it.lowercase()
        //...
    }

}

fun helloWorld(nome : String) : String{
    return "Hello $nome!"
}

fun helloWorld() : Unit{
    println("Hello World!")
}

fun oneLine() = println("One line fun")

fun sum(value : Int) : Int {
    return value + value
}
fun sumOneLine(value : Int) : Int =  value + value

fun saudacao(dia : Boolean) : String{
    return if(dia){
        "Bom dia"
    }else{
        "Boa noite"
    }
}
    fun saudacaoSimp(dia : Boolean)  = if(dia){
            "Bom dia"
        }else{
            "Boa noite"
        }

    fun bonusWhen(vlr : Int) : String{
        return when(vlr){
            2000 -> "2000"
            else -> "Valor fora"
        }
    }

