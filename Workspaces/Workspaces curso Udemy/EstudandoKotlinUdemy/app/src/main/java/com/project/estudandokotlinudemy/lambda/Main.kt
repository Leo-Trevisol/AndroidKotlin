package com.project.estudandokotlinudemy.lambda

import org.apache.tools.ant.taskdefs.condition.Not
import java.lang.Double.max
import java.lang.Double.min
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round


// {parametros -> corpo}
fun main() {

    val a = {
        println("Teste lambda")
    }
    a()

    receiveLambaA(a)


    receiveLambaA {
        println("Teste lambda")
    }


    val b = {
        x: Int -> x * x
    }
    b(10)

    receiveLambaB(b)

    receiveLambaB {x: Int -> x * x }


    val c = {
            x: Int, y: Int -> x * y
    }

    c(10,15)

    receiveLambaC(c)

    receiveLambaC {x: Int, y: Int -> x * y}

}

fun receiveLambaA(lambda : () -> Unit){
}

fun receiveLambaB(lambda : (x: Int) -> Int){
}

fun receiveLambaC(lambda : (x: Int, y: Int) -> Int){
}




