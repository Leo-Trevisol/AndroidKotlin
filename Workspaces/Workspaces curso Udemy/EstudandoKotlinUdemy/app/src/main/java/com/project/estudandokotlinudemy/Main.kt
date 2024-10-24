package com.project.estudandokotlinudemy

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



}